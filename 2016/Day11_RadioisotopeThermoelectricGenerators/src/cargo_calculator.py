from src.radiation_containment_simulator import RadiationContainmentSimulator
from src.simulator_state import SimulatorState


class CargoCalculator:
  @staticmethod
  def calculate_cargos(state:SimulatorState) -> list[list[str]]:
    current_rad_sim = RadiationContainmentSimulator(state)
    available_items = current_rad_sim.current_floor_items()
    return CargoCalculator.calculate_cargo_combinations(available_items)

  @staticmethod
  def calculate_cargo_combinations(items:list[str]) -> list[list[str]]:
    cargo_combos = []
    for item in items:
      cargo_combos.append([item])
    for i, first_item in enumerate(items):
      for j in range(i + 1, len(items)):
        cargo_combos.append([first_item, items[j]])
    return cargo_combos
