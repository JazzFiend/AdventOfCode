from src.wall_detector.wall_detector import WallDetector


class LookUpWallDetector(WallDetector):
  def __init__(self):
    self.walls = None

  def add_wall(self, wall:tuple[int, int]):
    if self.walls is None:
      self.walls = {wall}
    self.walls.add(wall)

  def is_wall(self, point:tuple[int, int]) -> bool:
    if self.walls is not None and point in self.walls:
      return True
    return False
