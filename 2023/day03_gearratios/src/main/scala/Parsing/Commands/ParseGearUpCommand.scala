package Parsing.Commands

import Parser.SchematicNumberParser
import Parsing.Commands.ParseGearCommand


class ParseGearUpCommand extends ParseGearCommand {
  override def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if (gearLocation._2 <= 0) { return 0 }

    val numberLocation = (gearLocation._1, gearLocation._2 - 1)
    SchematicNumberParser.parseNumber(schematic(numberLocation._2), numberLocation._1)
  }
}
