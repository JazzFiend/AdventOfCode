class FriedChipError(RuntimeError):
  @staticmethod
  def format_error_message(microchip:str, floor_num: int, floor:list) -> str:
    return microchip + " was fried. Floor " + str(floor_num) + ": " + str(floor)
