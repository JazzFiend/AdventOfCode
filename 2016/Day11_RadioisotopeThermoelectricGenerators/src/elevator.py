from errors.elevator_capacity_error import ElevatorCapacityError
from errors.elevator_pickup_error import ElevatorPickupError

from src.floors import Floors


class Elevator:
  def __init__(self, floor:int):
    self.floor = floor

  def get_floor(self) -> int:
    return self.floor

  def move_to_floor(self, floor:int):
    self.floor = floor

  def elevator_move(self, cargo:list[str], next_floor:int, floors:Floors):
    self.check_cargo_capacity(cargo)
    self.move_cargo(cargo, next_floor, floors)
    self.move_to_floor(next_floor)

  @staticmethod
  def check_cargo_capacity(cargo:list[str]):
    if len(cargo) < 1:
      raise ElevatorCapacityError('The elevator cannot move without cargo.')
    if len(cargo) > 2:
      raise ElevatorCapacityError('The elevator cannot carry ' + str(len(cargo)) + ' items.')

  def move_cargo(self, cargo:list[str], next_floor:int, floors:Floors):
    for item in cargo:
      if self.floor != floors.get_item_floor(item):
        self.throw_pickup_error(item, floors)
      floors.move_item_to_floor(item, next_floor)
    floors.check_fried_microchips(next_floor)

  def throw_pickup_error(self, item:str, floors:Floors):
    error = ('Item not is not on the same floor as the elevator:' +
      'Elevator Floor: ' + str(self.floor) +
      ' Item Name: ' + item + ' Floor: ' + str(floors.get_item_floor(item)))
    raise ElevatorPickupError(error)
