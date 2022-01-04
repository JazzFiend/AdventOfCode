import unittest

from src.cargo_calculator import CargoCalculator
from src.simulator_state import SimulatorState


class TestCargoCalculator(unittest.TestCase):
  def test_no_cargo(self):
    state = SimulatorState(0)
    cargo_list = CargoCalculator.calculate_cargos(state)
    self.assertEqual(cargo_list, [])

  def test_one_item(self):
    state = SimulatorState(0)
    state.add_item_to_floor('Z', 0)
    cargo_list = CargoCalculator.calculate_cargos(state)
    self.assertEqual(cargo_list, [['Z']])

  def test_two_items(self):
    state = SimulatorState(0)
    state.add_item_to_floor('Z', 0)
    state.add_item_to_floor('Y', 0)
    cargo_list = CargoCalculator.calculate_cargos(state)
    self.assertEqual(cargo_list, [['Z'], ['Y'], ['Z', 'Y']])

  def test_three_items(self):
    state = SimulatorState(0)
    state.add_item_to_floor('Z', 0)
    state.add_item_to_floor('Y', 0)
    state.add_item_to_floor('X', 0)
    cargo_list = CargoCalculator.calculate_cargos(state)
    self.assertEqual(cargo_list, [['Z'], ['Y'], ['X'], ['Z', 'Y'], ['Z', 'X'], ['Y', 'X']])

  def test_items_on_multiple_floors(self):
    state = SimulatorState(0)
    state.add_item_to_floor('Z', 0)
    state.add_item_to_floor('Y', 0)
    state.add_item_to_floor('X', 0)
    state.add_item_to_floor('A', 1)
    state.add_item_to_floor('B', 2)
    state.add_item_to_floor('C', 3)
    cargo_list = CargoCalculator.calculate_cargos(state)
    self.assertEqual(cargo_list, [['Z'], ['Y'], ['X'], ['Z', 'Y'], ['Z', 'X'], ['Y', 'X']])

  def test_acceptance(self):
    state = SimulatorState(1)
    state.add_item_to_floor('TmG', 1)
    state.add_item_to_floor('RuG', 1)
    state.add_item_to_floor('RuM', 1)
    state.add_item_to_floor('CmG', 1)
    state.add_item_to_floor('CmM', 1)
    cargo_list = CargoCalculator.calculate_cargos(state)
    self.assertEqual(cargo_list, [
      ['TmG'], ['RuG'], ['RuM'], ['CmG'], ['CmM'],
      ['TmG', 'RuG'], ['TmG', 'RuM'], ['TmG', 'CmG'], ['TmG', 'CmM'],
      ['RuG', 'RuM'], ['RuG', 'CmG'], ['RuG', 'CmM'],
      ['RuM', 'CmG'], ['RuM', 'CmM'],
      ['CmG', 'CmM'],
    ])
