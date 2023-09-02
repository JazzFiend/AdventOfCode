from src.wall_detector.wall_detector import WallDetector


class FormulaicWallDetector(WallDetector):
  def __init__(self, seed:int):
    self.favorite_number = seed

  def is_wall(self, point: tuple[int, int]) -> bool:
    x = point[0]
    y = point[1]
    favorite_sum = (x*x + 3*x + 2*x*y + y + y*y) + self.favorite_number
    one_count = format(favorite_sum, 'b').count('1')
    if one_count % 2 == 0:
      return False
    return True
