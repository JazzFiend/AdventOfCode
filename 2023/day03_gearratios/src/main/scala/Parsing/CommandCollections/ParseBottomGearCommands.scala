package Parsing.CommandCollections

import Parsing.Commands.{ParseGearDownCommand, ParseGearLowerLeftCommand, ParseGearLowerRightCommand}

class ParseBottomGearCommands extends ParseGearCommandCollection {
  override def executeAll(schematic: List[String], gearLocation: (Int, Int)): List[Int] = {
    val checkBottom = ParseGearDownCommand().execute(schematic, gearLocation)
    if (checkBottom == 0) {
      val topCommands = List(ParseGearLowerLeftCommand(), ParseGearLowerRightCommand())
      return topCommands.map(command => command.execute(schematic, gearLocation))
    }
    List(checkBottom)
  }
}
