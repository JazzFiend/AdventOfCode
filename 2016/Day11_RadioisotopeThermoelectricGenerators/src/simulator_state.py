from src.floors import Floors


class SimulatorState:
  def __init__(self, elevator_floor:int):
    self.elevator_floor = elevator_floor
    self.floors = [[], [], [], []]

  def add_item_to_floor(self, item:str, floor:int):
    self.floors[floor].append(item)

  def get_elevator_floor(self) -> int:
    return self.elevator_floor

  def get_floors(self) -> list[list[str]]:
    return self.floors

  def set_floors(self, value:Floors):
    self.floors = value.get_floors()

  def is_complete(self) -> bool:
    floors_to_validate = self.floors
    return (
      len(floors_to_validate[0]) == 0
      and len(floors_to_validate[1]) == 0
      and len(floors_to_validate[2]) == 0
      and len(floors_to_validate[3]) > 0
    )

  @staticmethod
  def equivalent_states(s_1, s_2) -> bool:
    if s_1.get_elevator_floor() != s_2.get_elevator_floor():
      return False
    s1_pairs = Floors(s_1.get_floors()).generate_pairs()
    s2_pairs = Floors(s_2.get_floors()).generate_pairs()
    if len(s1_pairs) != len(s2_pairs):
      return False
    unmatched = s1_pairs
    for s2_pair in s2_pairs:
      try:
        unmatched.remove(s2_pair)
      except ValueError:
        return False
    return True

  def __hash__(self) -> int:
    hash_sum = hash(self.elevator_floor)
    for i in range(0, 3):
      inner_sum = 0
      for item in self.floors[i]:
        inner_sum += hash(item)
      hash_sum += (inner_sum * (i + 1))
    return hash_sum

  def __eq__(self, __o: object) -> bool:
    if self.elevator_floor != __o.get_elevator_floor():
      return False
    for i in range(0, 3):
      if len(self.floors[i]) != len(__o.get_floors()[i]):
        return False
      for item in self.floors[i]:
        try:
          __o.get_floors()[i].index(item)
        except ValueError:
          return False
    return True

  def __str__(self) -> str:
    string = "Elevator: " + str(self.elevator_floor) + " "
    string += "Floor 0: " + str(self.floors[0]) + " "
    string += "Floor 1: " + str(self.floors[1]) + " "
    string += "Floor 2: " + str(self.floors[2]) + " "
    string += "Floor 3: " + str(self.floors[3])
    return string
