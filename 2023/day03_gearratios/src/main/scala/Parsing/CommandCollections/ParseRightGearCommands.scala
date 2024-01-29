package Parsing.CommandCollections

import Parsing.Commands.ParseGearRightCommand

class ParseRightGearCommands extends ParseGearCommandCollection {
  override def executeAll(schematic: List[String], gearLocation: (Int, Int)): List[Int] = {
    List(ParseGearRightCommand().execute(schematic, gearLocation))
  }
}