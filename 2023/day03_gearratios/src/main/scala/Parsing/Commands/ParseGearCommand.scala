package Parsing.Commands

abstract class ParseGearCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int
}
