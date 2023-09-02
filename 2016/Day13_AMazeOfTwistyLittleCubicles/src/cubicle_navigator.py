from src.wall_detector.wall_detector import WallDetector


class CubicleNavigator:
  def __init__(self, wall_detector:WallDetector):
    self.wall_detector = wall_detector
    self.visited = {}
    self.to_visit = []

  def find_shortest_path(self, start:tuple[int, int], end:tuple[int, int]) -> int:
    self.__setup(start)

    while len(self.to_visit) > 0:
      current_point = self.__remove_next_point()
      move_count = self.visited[current_point]

      if current_point == end:
        return move_count
      self.__consider_adjacent_points(current_point, move_count + 1)

    raise RuntimeError("No path to point exists")

  def calculate_points_by_distance(self, distance:int, start:tuple[int, int]) -> int:
    self.__setup(start)

    while len(self.to_visit) > 0:
      current_point = self.__remove_next_point()
      move_count = self.visited[current_point]

      if move_count > distance:
        del self.visited[current_point]
      else:
        self.__consider_adjacent_points(current_point, move_count + 1)

    return len(self.visited)

  def __consider_adjacent_points(self, current_point, move_count):
    possible_moves = self.__calculate_cardinal_direction_moves(current_point)
    for move in possible_moves:
      if self.__is_visitable(move) and move not in self.visited:
        self.visited[move] = move_count
        self.to_visit.append(move)

  def __setup(self, start):
    self.to_visit = [start]
    self.visited = {}
    self.visited[start] = 0

  def __remove_next_point(self):
    current_point = self.to_visit[0]
    del self.to_visit[0]
    return current_point

  def __calculate_cardinal_direction_moves(self, point:tuple[int, int]) -> list[tuple[int, int]]:
    possible_moves = [
        (point[0] - 1, point[1]),
        (point[0] + 1, point[1]),
        (point[0], point[1] - 1),
        (point[0], point[1] + 1)
      ]
    return possible_moves

  def __is_visitable(self, point:tuple[int, int]) -> bool:
    result = point[0] >= 0 and point[1] >= 0 and not self.wall_detector.is_wall(point)
    return result
