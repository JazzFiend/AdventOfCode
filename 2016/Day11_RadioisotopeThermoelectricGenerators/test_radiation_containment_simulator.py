import unittest

from errors.elevator_capacity_error import ElevatorCapacityError
from errors.elevator_move_error import ElevatorMoveError
from errors.elevator_pickup_error import ElevatorPickupError
from errors.fried_chip_error import FriedChipError
from src.radiation_containment_simulator import RadiationContainmentSimulator
from src.simulator_state import SimulatorState

class RadiationContainmentTest(unittest.TestCase):
  def assert_simulator_state(self, simulator_state, elevator_floor, element_map):
    self.assert_elevator_location(simulator_state, elevator_floor)
    expected_floor_map = self.create_floor_map(element_map)
    self.assert_floors(simulator_state, expected_floor_map)

  def assert_elevator_location(self, simulator_state:SimulatorState, elevator_floor):
    self.assertEqual(simulator_state.get_elevator_floor(), elevator_floor)

  @staticmethod
  def create_floor_map(element_map):
    expected_floor_map = [[], [], [], []]
    for element in element_map:
      if 'generator' in element:
        expected_floor_map[element['generator']['floor']].append(element['generator']['name'])
      if 'microchip' in element:
        expected_floor_map[element['microchip']['floor']].append(element['microchip']['name'])
    return expected_floor_map

  def assert_floors(self, simulator_state:SimulatorState, expected_floor_map):
    for (i, floor) in enumerate(simulator_state.get_floors()):
      self.assertCountEqual(floor, expected_floor_map[i])

  def test_cannot_move_empty_elevator(self):
    with self.assertRaises(ElevatorCapacityError):
      rad_sim = RadiationContainmentSimulator(SimulatorState(0))
      rad_sim.elevator_up([])

  def test_cannot_move_elevator_outside_building(self):
    with self.assertRaises(ElevatorMoveError):
      start = SimulatorState(3)
      start.add_item_to_floor('CoG', 0)
      start.add_item_to_floor('CoM', 0)
      rad_sim = RadiationContainmentSimulator(start)
      rad_sim.elevator_up(['CoM'])

  def test_cannot_move_elevator_into_basement(self):
    with self.assertRaises(ElevatorMoveError):
      start = SimulatorState(0)
      start.add_item_to_floor('CaG', 0)
      start.add_item_to_floor('CaM', 0)
      rad_sim = RadiationContainmentSimulator(start)
      rad_sim.elevator_down(['CaM'])

  def test_cannot_pickup_item_on_different_floor(self):
    with self.assertRaises(ElevatorPickupError):
      start = SimulatorState(0)
      start.add_item_to_floor('HG', 2)
      start.add_item_to_floor('HM', 1)
      rad_sim = RadiationContainmentSimulator(start)
      rad_sim.elevator_up(['HM'])

  def test_cannot_exceed_two_items_in_elevator(self):
    with self.assertRaises(ElevatorCapacityError):
      start = SimulatorState(0)
      start.add_item_to_floor('HG', 0)
      start.add_item_to_floor('HM', 0)
      start.add_item_to_floor('XeG', 0)
      start.add_item_to_floor('XeM', 0)
      rad_sim = RadiationContainmentSimulator(start)
      rad_sim.elevator_up(['HM', 'HG', 'XeM'])

  def test_cannot_move_item_that_does_not_exist(self):
    with self.assertRaises(RuntimeError):
      rad_sim = RadiationContainmentSimulator(SimulatorState(0))
      rad_sim.elevator_up(['CaM'])

  def test_move_elevator_with_one_item(self):
    start = SimulatorState(0)
    start.add_item_to_floor('HG', 0)
    start.add_item_to_floor('HM', 1)
    expected_floor_map = [
      {
        'generator': {
          'floor': 1,
          'name': 'HM'
        },
        'microchip': {
          'floor': 1,
          'name': 'HG'
        }
      }
    ]
    rad_sim = RadiationContainmentSimulator(start)
    rad_sim.elevator_up(['HG'])
    self.assert_simulator_state(rad_sim.get_simulator_state(), 1, expected_floor_map)

  def test_move_elevator_with_two_items(self):
    start = SimulatorState(0)
    start.add_item_to_floor('HG', 2)
    start.add_item_to_floor('HM', 0)
    start.add_item_to_floor('NG', 2)
    start.add_item_to_floor('NM', 0)
    expected_floor_map = [
      {
        'generator': {
          'floor': 2,
          'name': 'HG'
        },
        'microchip': {
          'floor': 1,
          'name': 'HM'
        }
      },
      {
        'generator': {
          'floor': 2,
          'name': 'NG'
        },
        'microchip': {
          'floor': 1,
          'name': 'NM'
        }
      }
    ]
    rad_sim = RadiationContainmentSimulator(start)
    rad_sim.elevator_up(['NM', 'HM'])
    self.assert_simulator_state(rad_sim.get_simulator_state(), 1, expected_floor_map)

  def test_chip_fried_when_exposed_to_other_generator(self):
    with self.assertRaises(FriedChipError):
      start = SimulatorState(0)
      start.add_item_to_floor('HG', 0)
      start.add_item_to_floor('HM', 0)
      start.add_item_to_floor('XeG', 1)
      start.add_item_to_floor('XeM', 0)
      rad_sim = RadiationContainmentSimulator(start)
      rad_sim.elevator_up(['HM'])

  @staticmethod
  def test_check_fried_chips_after_all_cargo_moved():
    start = SimulatorState(2)
    start.add_item_to_floor('HG', 2)
    start.add_item_to_floor('HM', 2)
    start.add_item_to_floor('LiG', 2)
    start.add_item_to_floor('LiM', 3)
    rad_sim = RadiationContainmentSimulator(start)
    rad_sim.elevator_up(['HG', 'LiG'])
    # No exception should be thrown

if __name__ == '__main__':
  unittest.main()
