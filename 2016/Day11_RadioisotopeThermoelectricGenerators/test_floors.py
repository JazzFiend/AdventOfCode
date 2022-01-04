import unittest

from errors.fried_chip_error import FriedChipError
from src.floors import Floors

class FloorsTest(unittest.TestCase):
  def test_throw_fried_check_microchip(self):
    with self.assertRaises(FriedChipError):
      floors = Floors([['AM', 'BG']])
      floors.check_fried_microchips(0)

  def test_throw_fried_check_generator(self):
    with self.assertRaises(FriedChipError):
      floors = Floors([['AM', 'BG']])
      floors.check_fried_microchips(0)

  @staticmethod
  def test_not_fried_check_microchip():
    floors = Floors([['BM', 'BG']])
    floors.check_fried_microchips(0)
    floors.check_fried_microchips(0)

  @staticmethod
  def test_protected():
    floors = Floors([['AM', 'AG', 'BG']])
    floors.check_fried_microchips(0)
    floors.check_fried_microchips(0)
    floors.check_fried_microchips(0)

  def test_get_floor_pairs(self):
    floors = Floors([['AG'], ['BM'], ['AM'], ['BG']])
    expected = [[2, 0], [1, 3]]
    pairs = floors.generate_pairs()
    self.assertEqual(len(expected), len(pairs))
    for pair in pairs:
      self.assertTrue(pair in expected)
