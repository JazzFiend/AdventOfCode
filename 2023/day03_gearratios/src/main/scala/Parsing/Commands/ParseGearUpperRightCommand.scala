package Parsing.Commands

import Parser.SchematicNumberParser
import Parsing.Commands.ParseGearCommand

class ParseGearUpperRightCommand extends ParseGearCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 == schematic.head.length - 1 || gearLocation._2 == 0) { return 0 }

    val startingPoint = (gearLocation._1 + 1, gearLocation._2 - 1)
    SchematicNumberParser.parseNumber(schematic(startingPoint._2), startingPoint._1)
  }
}
