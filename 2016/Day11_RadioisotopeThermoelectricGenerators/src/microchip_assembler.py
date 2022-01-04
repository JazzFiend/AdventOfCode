import sys

from errors.elevator_move_error import ElevatorMoveError
from errors.fried_chip_error import FriedChipError

from src.cargo_calculator import CargoCalculator
from src.radiation_containment_simulator import RadiationContainmentSimulator
from src.simulator_state import SimulatorState


class MicrochipAssembler:
  def __init__(self):
    self.new_states = {}
    self.seen_states = {}
    self.min_moves_to_completion = sys.maxsize
    self.heuristic_value_map = {}

  def solve(self, setup:SimulatorState) -> int:
    self.setup_solve(setup)

    while len(self.new_states) > 0:
      best = max(self.heuristic_value_map.keys())
      next_setup = self.heuristic_value_map[best][0]
      step_count = self.new_states[next_setup]
      self.remove_current_state(best, next_setup)

      if step_count >= self.min_moves_to_completion:
        continue

      available_cargos = CargoCalculator.calculate_cargos(next_setup)
      for cargo in available_cargos:
        self.calculate_new_states_up(next_setup, cargo, step_count)
        if MicrochipAssembler.consider_down(next_setup):
          self.calculate_new_states_down(next_setup, cargo, step_count)
    return self.min_moves_to_completion

  def setup_solve(self, setup:SimulatorState):
    self.new_states = {setup: 0}
    self.seen_states = {setup: 0}
    self.min_moves_to_completion = sys.maxsize
    self.heuristic_value_map = {self.calculate_heuristic(setup): [setup]}

  def remove_current_state(self, best, next_setup):
    self.new_states.pop(next_setup)
    self.heuristic_value_map[best].remove(next_setup)
    if self.heuristic_value_map[best] == []:
      self.heuristic_value_map.pop(best)

  @staticmethod
  def calculate_heuristic(state: SimulatorState) -> int:
    floors = state.get_floors()
    total = 4 * len(floors[3])
    total += 3 * len(floors[2])
    total += 2 * len(floors[1])
    total += len(floors[0])
    return total

  @staticmethod
  def consider_down(state:SimulatorState) -> bool:
    current_floor = state.get_elevator_floor()
    floors = state.get_floors()
    for i in range(current_floor):
      if len(floors[i]) > 0:
        return True
    return False

  def calculate_new_states_up(self, state, cargo, step_count):
    try:
      next_state = MicrochipAssembler.advance_simulator_up(state, cargo)
      new_step_count = step_count + 1
      if next_state.is_complete():
        self.set_min_value(new_step_count)
      else:
        self.update_state(new_step_count, next_state)
        # self.update_state_simple_states(new_step_count, next_state)
    except (ElevatorMoveError, FriedChipError):
      self.handle_bad_move_error()

  def calculate_new_states_down(self, state, cargo, step_count):
    try:
      next_state = MicrochipAssembler.advance_simulator_down(state, cargo)
      new_step_count = step_count + 1
      self.update_state(new_step_count, next_state)
      # self.update_state_simple_states(new_step_count, next_state)
    except (ElevatorMoveError, FriedChipError):
      self.handle_bad_move_error()

  @staticmethod
  def advance_simulator_up(state, cargo) -> SimulatorState:
    rad_sim = RadiationContainmentSimulator(state)
    rad_sim.elevator_up(cargo)
    return rad_sim.get_simulator_state()

  @staticmethod
  def advance_simulator_down(state, cargo) -> SimulatorState:
    rad_sim = RadiationContainmentSimulator(state)
    rad_sim.elevator_down(cargo)
    return rad_sim.get_simulator_state()

  def handle_bad_move_error(self):
    # Swallow exception. Don't add a new state.
    pass

  def set_min_value(self, step_count):
    self.min_moves_to_completion = min(self.min_moves_to_completion, step_count)

  def update_state(self, step_count:int, state:SimulatorState):
    if state in self.seen_states:
      if self.seen_states[state] > step_count:
        self.seen_states[state] = step_count
    else:
      self.seen_states[state] = step_count
      self.new_states[state] = step_count
      heuristic = self.calculate_heuristic(state)
      if heuristic in self.heuristic_value_map:
        self.heuristic_value_map[heuristic].append(state)
      else:
        self.heuristic_value_map[heuristic] = [state]

  # I thought this would be an optimization. Since all of the generators are interchangable, this
  # checks for equivalent states instead of equal states. But it takes way longer for some reason.
  def update_state_simple_states(self, step_count:int, state:SimulatorState):
    equiv_state = None
    found_equivalent = False
    for seen_state in self.seen_states:
      if SimulatorState.equivalent_states(seen_state, state):
        found_equivalent = True
        equiv_state = seen_state
    if found_equivalent:
      if self.seen_states[equiv_state] > step_count:
        self.seen_states[equiv_state] = step_count
    else:
      self.seen_states[state] = step_count
      self.new_states[state] = step_count
      heuristic = self.calculate_heuristic(state)
      if heuristic in self.heuristic_value_map:
        self.heuristic_value_map[heuristic].append(state)
      else:
        self.heuristic_value_map[heuristic] = [state]
