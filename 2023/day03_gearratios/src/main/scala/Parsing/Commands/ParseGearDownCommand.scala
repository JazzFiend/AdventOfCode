package Parsing.Commands

import Parser.SchematicNumberParser

class ParseGearDownCommand extends ParseGearCommand {
  override def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if (gearLocation._2 >= schematic.size - 1) {
      return 0
    }

    val numberLocation = (gearLocation._1, gearLocation._2 + 1)
    SchematicNumberParser.parseNumber(schematic(numberLocation._2), numberLocation._1)
  }
}
