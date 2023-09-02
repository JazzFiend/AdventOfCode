import pytest

from src.wall_detector.formulaic_wall_detector import FormulaicWallDetector

def test_parameterized_find_walls():
  test_scenarios = [
    ((0, 0), True),
    ((1, 1), False),
    ((2, 3), True),
    ((5, 8), False),
    ((19, 11), True)
  ]

  wall_detector = FormulaicWallDetector(1350)
  for scenario in test_scenarios:
    assert wall_detector.is_wall(scenario[0]) == scenario[1]
