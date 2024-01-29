package Parsing.CommandCollections

import Parsing.Commands.{ParseGearUpCommand, ParseGearUpperLeftCommand, ParseGearUpperRightCommand}

class ParseTopGearCommands extends ParseGearCommandCollection {
  override def executeAll(schematic: List[String], gearLocation: (Int, Int)): List[Int] = {
    val checkTop = ParseGearUpCommand().execute(schematic, gearLocation)
    if (checkTop == 0) {
      val topCommands = List(ParseGearUpperLeftCommand(), ParseGearUpperRightCommand())
      return topCommands.map(command => command.execute(schematic, gearLocation))
    }
    List(checkTop)
  }
}
