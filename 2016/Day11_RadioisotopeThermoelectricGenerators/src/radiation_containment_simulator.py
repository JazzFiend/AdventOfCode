from errors.elevator_move_error import ElevatorMoveError

from src.elevator import Elevator
from src.floors import Floors
from src.simulator_state import SimulatorState


class RadiationContainmentSimulator:
  def __init__(self, initial_state:SimulatorState):
    self.floors = Floors(initial_state.floors)
    self.elevator = Elevator(initial_state.get_elevator_floor())

  def get_simulator_state(self) -> SimulatorState:
    state = SimulatorState(self.elevator.get_floor())
    state.set_floors(self.floors)
    return state

  def current_floor_items(self) -> list[str]:
    elevator_floor = self.elevator.get_floor()
    return self.floors.get_floors()[elevator_floor]

  def elevator_up(self, cargo:list[str]):
    one_floor_up = self.elevator.get_floor() + 1
    if one_floor_up > 3:
      raise ElevatorMoveError('Cannot go above fourth floor')
    self.elevator.elevator_move(cargo, one_floor_up, self.floors)

  def elevator_down(self, cargo:list[str]):
    one_floor_down = self.elevator.get_floor() - 1
    if one_floor_down < 0:
      raise ElevatorMoveError('Cannot go below zeroth floor')
    self.elevator.elevator_move(cargo, one_floor_down, self.floors)
