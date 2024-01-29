package Parsing.Commands

import Parser.SchematicNumberParser
import Parsing.Commands.ParseGearCommand


class ParseGearRightCommand extends ParseGearCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 >= schematic.head.length - 1) { return 0 }

    SchematicNumberParser.parseNumber(schematic(gearLocation._2), gearLocation._1 + 1)
  }
}
