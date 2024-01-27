package ParseCommands

import Parser.SchematicNumberParser


class ParseGearUpCommand extends ParseGearCommand {
  override def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._2 <= 0) { return 0 }

    val numberLocation = (gearLocation._1, gearLocation._2 - 1)
    if(schematic(numberLocation._2)(numberLocation._1).isDigit) {
      if (numberLocation._1 > 0 && schematic(numberLocation._2)(numberLocation._1 - 1).isDigit) {
        return SchematicNumberParser.parseNumberToLeft(schematic(numberLocation._2), numberLocation._1)
      } else {
        return SchematicNumberParser.parseNumberToRight(schematic(numberLocation._2), numberLocation._1)
      }
    }
    0
  }
}
