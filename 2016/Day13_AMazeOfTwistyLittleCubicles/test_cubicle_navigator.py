import pytest

from src.cubicle_navigator import CubicleNavigator
from src.wall_detector.formulaic_wall_detector import FormulaicWallDetector
from src.wall_detector.look_up_wall_detector import LookUpWallDetector

def test_start_end_identical():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((0, 0), (0, 0), ) == 0

def test_move_one_space():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((0, 0), (0, 1)) == 1

def test_move_two_spaces_different_directions():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((0, 0), (1, 1)) == 2

def test_move_many_spaces():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((0, 0), (5, 9)) == 14

def test_wall_between_two_spaces():
  wall_detector = LookUpWallDetector()
  wall_detector.add_wall((0, 1))
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((0, 0), (0, 2)) == 4

def test_multiple_walls():
  wall_detector = LookUpWallDetector()
  wall_detector.add_wall((1, 0))
  wall_detector.add_wall((1, 1))
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((0, 0), (2, 0)) == 6

def test_no_path():
  with pytest.raises(Exception):
    wall_detector = LookUpWallDetector()
    wall_detector.add_wall((1, 0))
    wall_detector.add_wall((0, 1))
    nav = CubicleNavigator(wall_detector)
    nav.find_shortest_path((0, 0), (2, 0))

def test_acceptance():
  wall_detector = LookUpWallDetector()
  wall_detector.add_wall((1, 0))
  wall_detector.add_wall((3, 0))
  wall_detector.add_wall((4, 0))
  wall_detector.add_wall((5, 0))
  wall_detector.add_wall((6, 0))
  wall_detector.add_wall((8, 0))
  wall_detector.add_wall((9, 0))

  wall_detector.add_wall((2, 1))
  wall_detector.add_wall((5, 1))
  wall_detector.add_wall((9, 1))

  wall_detector.add_wall((0, 2))
  wall_detector.add_wall((5, 2))
  wall_detector.add_wall((6, 2))

  wall_detector.add_wall((0, 3))
  wall_detector.add_wall((1, 3))
  wall_detector.add_wall((2, 3))
  wall_detector.add_wall((4, 3))
  wall_detector.add_wall((6, 3))
  wall_detector.add_wall((7, 3))
  wall_detector.add_wall((8, 3))

  wall_detector.add_wall((1, 4))
  wall_detector.add_wall((2, 4))
  wall_detector.add_wall((5, 4))
  wall_detector.add_wall((8, 4))

  wall_detector.add_wall((2, 5))
  wall_detector.add_wall((3, 5))
  wall_detector.add_wall((8, 5))

  wall_detector.add_wall((0, 6))
  wall_detector.add_wall((4, 6))
  wall_detector.add_wall((5, 6))
  wall_detector.add_wall((7, 6))
  wall_detector.add_wall((8, 6))
  wall_detector.add_wall((9, 6))
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((1, 1), (7, 4)) == 11

def test_puzzle_one():
  wall_detector = FormulaicWallDetector(1350)
  nav = CubicleNavigator(wall_detector)
  assert nav.find_shortest_path((1, 1), (31, 39)) == 92

def test_distance_never_move():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.calculate_points_by_distance(0, (1, 1)) == 1

def test_distance_move_one_space():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.calculate_points_by_distance(1, (1, 1)) == 5

def test_distance_move_many_spaces():
  wall_detector = LookUpWallDetector()
  nav = CubicleNavigator(wall_detector)
  assert nav.calculate_points_by_distance(3, (3, 3)) == 25

def test_distance_one_wall():
  wall_detector = LookUpWallDetector()
  wall_detector.add_wall((1, 0))
  nav = CubicleNavigator(wall_detector)
  assert nav.calculate_points_by_distance(1, (0, 0)) == 2

def test_distance_cant_move():
  wall_detector = LookUpWallDetector()
  wall_detector.add_wall((1, 0))
  wall_detector.add_wall((0, 1))
  nav = CubicleNavigator(wall_detector)
  assert nav.calculate_points_by_distance(1, (0, 0)) == 1

def test_puzzle_two():
  wall_detector = FormulaicWallDetector(1350)
  nav = CubicleNavigator(wall_detector)
  assert nav.calculate_points_by_distance(50, (1, 1)) == 124
