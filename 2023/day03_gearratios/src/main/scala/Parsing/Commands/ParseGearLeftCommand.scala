package Parsing.Commands

import Parser.SchematicNumberParser
import Parsing.Commands.ParseGearCommand

class ParseGearLeftCommand extends ParseGearCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 <= 0) { return 0 }

    SchematicNumberParser.parseNumber(schematic(gearLocation._2), gearLocation._1 - 1)
  }
}
