from errors.fried_chip_error import FriedChipError

class Floors:
  def __init__(self, state:list[list[str]]):
    floors_value = []
    for floor in state:
      new_floor = []
      for item in floor:
        new_floor.append(item)
      floors_value.append(new_floor)
    self.floors = floors_value

  def move_item_to_floor(self, item:str, floor:int):
    origin_floor = self.get_item_floor(item)
    self.floors[origin_floor].remove(item)
    self.floors[floor].append(item)

  def get_item_floor(self, item:str) -> int:
    for (i, floor) in enumerate(self.floors):
      if item in floor:
        return i
    raise RuntimeError(item + ' not found')

  def check_fried_microchips(self, floor:int):
    microchips = list(filter(Floors.is_microchip, self.floors[floor]))
    generators = list(filter(Floors.is_generator, self.floors[floor]))
    for chip in microchips:
      if len(generators) > 0 and not Floors.chip_protected(chip, generators):
        raise FriedChipError(FriedChipError.format_error_message(chip, floor, self.floors[floor]))

  @staticmethod
  def chip_protected(chip, generators) -> bool:
    return any(map(lambda x: Floors.is_matching_generator(chip, x), generators))

  @staticmethod
  def is_microchip(item:str) -> bool:
    return item[(len(item) - 1)] == 'M'

  @staticmethod
  def is_generator(item:str) -> bool:
    return item[(len(item) - 1)] == 'G'

  @staticmethod
  def is_matching_generator(microchip:str, generator:str) -> bool:
    return microchip[0: len(microchip) - 1] == generator[0: len(generator) - 1]

  def get_floors(self) -> list[list[str]]:
    return self.floors

  def generate_pairs(self) -> list[list[int]]:
    microchips = []
    for floor in self.floors:
      microchips.extend(list(filter(Floors.is_microchip, floor)))

    floor_pairs = []
    for chip in microchips:
      chip_location = self.get_item_floor(chip)
      element = chip[0: len(chip) - 1]
      matching_generator = element + 'G'
      generator_location = self.get_item_floor(matching_generator)
      floor_pairs.append([chip_location, generator_location])

    return floor_pairs
