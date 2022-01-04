import unittest

from src.microchip_assembler import MicrochipAssembler
from src.simulator_state import SimulatorState


class MicrochipAssemblerTest(unittest.TestCase):
  def test_one_move_to_completion(self):
    assembler = MicrochipAssembler()
    start = SimulatorState(2)
    start.add_item_to_floor('HG', 2)
    start.add_item_to_floor('HM', 3)
    self.assertEqual(1, assembler.solve(start))

  def test_two_moves_to_completion(self):
    assembler = MicrochipAssembler()
    start = SimulatorState(1)
    start.add_item_to_floor('CoG', 1)
    start.add_item_to_floor('CoM', 3)
    self.assertEqual(2, assembler.solve(start))

  def test_backtrack_to_complete_simulation(self):
    assembler = MicrochipAssembler()
    start = SimulatorState(2)
    start.add_item_to_floor('AM', 2)
    start.add_item_to_floor('BM', 2)
    start.add_item_to_floor('CM', 2)
    start.add_item_to_floor('AG', 3)
    start.add_item_to_floor('BG', 3)
    start.add_item_to_floor('CG', 3)
    self.assertEqual(3, assembler.solve(start))

  def test_acceptance(self):
    assembler = MicrochipAssembler()
    start = SimulatorState(0)
    start.add_item_to_floor('HM', 0)
    start.add_item_to_floor('LiM', 0)
    start.add_item_to_floor('HG', 1)
    start.add_item_to_floor('LiG', 2)
    self.assertEqual(11, assembler.solve(start))

  # Currently takes 45 seconds to run.
  def test_puzzle_one(self):
    assembler = MicrochipAssembler()
    start = SimulatorState(0)
    start.add_item_to_floor('SrG', 0)
    start.add_item_to_floor('SrM', 0)
    start.add_item_to_floor('PuG', 0)
    start.add_item_to_floor('PuM', 0)
    start.add_item_to_floor('TmG', 1)
    start.add_item_to_floor('RuG', 1)
    start.add_item_to_floor('RuM', 1)
    start.add_item_to_floor('CmG', 1)
    start.add_item_to_floor('CmM', 1)
    start.add_item_to_floor('TmM', 2)

    self.assertEqual(37, assembler.solve(start))

  # Currently takes an hour to run. There's definitely more optimizations I can make to this to
  # make it run in a more managable amount of time, but I'm so burnt out on this I'm calling it
  # here.
  @unittest.skip("Need More Shortcuts")
  def test_puzzle_two(self):
    assembler = MicrochipAssembler()
    start = SimulatorState(0)
    start.add_item_to_floor('SrG', 0)
    start.add_item_to_floor('SrM', 0)
    start.add_item_to_floor('PuG', 0)
    start.add_item_to_floor('PuM', 0)
    start.add_item_to_floor('ElG', 0)
    start.add_item_to_floor('ElM', 0)
    start.add_item_to_floor('DlG', 0)
    start.add_item_to_floor('DlM', 0)
    start.add_item_to_floor('TmG', 1)
    start.add_item_to_floor('RuG', 1)
    start.add_item_to_floor('RuM', 1)
    start.add_item_to_floor('CmG', 1)
    start.add_item_to_floor('CmM', 1)
    start.add_item_to_floor('TmM', 2)

    self.assertEqual(61, assembler.solve(start))
