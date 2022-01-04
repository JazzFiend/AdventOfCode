import unittest

from src.simulator_state import SimulatorState

class SimulatorStateTest(unittest.TestCase):
  def test_equals(self):
    state1 = SimulatorState(2)
    state1.add_item_to_floor('AM', 2)
    state1.add_item_to_floor('BM', 2)
    state1.add_item_to_floor('AG', 1)
    state1.add_item_to_floor('BG', 1)

    state2 = SimulatorState(2)
    state2.add_item_to_floor('BM', 2)
    state2.add_item_to_floor('AM', 2)
    state2.add_item_to_floor('BG', 1)
    state2.add_item_to_floor('AG', 1)

    self.assertTrue(state1 == state2)

  def test_not_equals_missing_item(self):
    state1 = SimulatorState(2)
    state1.add_item_to_floor('AM', 2)
    state1.add_item_to_floor('BM', 2)
    state1.add_item_to_floor('AG', 1)
    state1.add_item_to_floor('BG', 1)

    state2 = SimulatorState(2)
    state2.add_item_to_floor('BM', 2)
    state2.add_item_to_floor('AM', 2)
    state2.add_item_to_floor('BG', 1)

    self.assertFalse(state1 == state2)

  def test_not_equals_wrong_item(self):
    state1 = SimulatorState(2)
    state1.add_item_to_floor('AM', 2)
    state1.add_item_to_floor('BM', 2)
    state1.add_item_to_floor('AG', 1)
    state1.add_item_to_floor('BG', 1)

    state2 = SimulatorState(2)
    state2.add_item_to_floor('BM', 1)
    state2.add_item_to_floor('AM', 1)
    state2.add_item_to_floor('BG', 2)
    state2.add_item_to_floor('AG', 2)

    self.assertFalse(state1 == state2)

  def test_not_equals_elevator(self):
    state1 = SimulatorState(1)
    state1.add_item_to_floor('AM', 2)
    state1.add_item_to_floor('AG', 1)

    state2 = SimulatorState(2)
    state2.add_item_to_floor('AM', 2)
    state2.add_item_to_floor('AG', 1)

    self.assertFalse(state1 == state2)

  def test_hash_matches(self):
    state1 = SimulatorState(2)
    state1.add_item_to_floor('AM', 2)
    state1.add_item_to_floor('BM', 2)
    state1.add_item_to_floor('AG', 1)
    state1.add_item_to_floor('BG', 1)

    state2 = SimulatorState(2)
    state2.add_item_to_floor('BM', 2)
    state2.add_item_to_floor('AM', 2)
    state2.add_item_to_floor('BG', 1)
    state2.add_item_to_floor('AG', 1)

    self.assertTrue(state1.__hash__() == state2.__hash__())

  def test_hash_does_not_match(self):
    state1 = SimulatorState(2)
    state1.add_item_to_floor('AM', 2)
    state1.add_item_to_floor('BM', 2)
    state1.add_item_to_floor('AG', 1)
    state1.add_item_to_floor('BG', 1)

    state2 = SimulatorState(3)
    state2.add_item_to_floor('BM', 2)
    state2.add_item_to_floor('AM', 2)
    state2.add_item_to_floor('BG', 1)
    state2.add_item_to_floor('AG', 1)

    self.assertFalse(state1.__hash__() == state2.__hash__())

  def test_simulation_complete(self):
    state = SimulatorState(0)
    state.add_item_to_floor('LiG', 3)
    state.add_item_to_floor('LiM', 3)
    self.assertTrue(state.is_complete())

  def test_simulation_not_complete(self):
    state = SimulatorState(2)
    state.add_item_to_floor('CG', 2)
    state.add_item_to_floor('AG', 3)
    state.add_item_to_floor('BG', 3)
    state.add_item_to_floor('AM', 3)
    state.add_item_to_floor('BM', 3)
    state.add_item_to_floor('CM', 3)
    self.assertFalse(state.is_complete())

  def test_equivalent_states(self):
    state1 = SimulatorState(2)
    state1.add_item_to_floor('HM', 0)
    state1.add_item_to_floor('HG', 1)
    state1.add_item_to_floor('LiG', 1)
    state1.add_item_to_floor('LiM', 2)

    state2 = SimulatorState(2)
    state2.add_item_to_floor('LiG', 1)
    state2.add_item_to_floor('LiM', 0)
    state2.add_item_to_floor('HG', 1)
    state2.add_item_to_floor('HM', 2)

    self.assertTrue(SimulatorState.equivalent_states(state1, state2))

  def test_different_states(self):
    state1 = SimulatorState(3)
    state1.add_item_to_floor('CM', 2)
    state1.add_item_to_floor('AG', 3)
    state1.add_item_to_floor('BG', 3)
    state1.add_item_to_floor('CG', 3)
    state1.add_item_to_floor('AM', 3)
    state1.add_item_to_floor('BM', 3)

    state2 = SimulatorState(3)
    state2.add_item_to_floor('BM', 2)
    state2.add_item_to_floor('CM', 2)
    state2.add_item_to_floor('AG', 3)
    state2.add_item_to_floor('BG', 3)
    state2.add_item_to_floor('CG', 3)
    state2.add_item_to_floor('AM', 3)

    self.assertFalse(SimulatorState.equivalent_states(state1, state2))
