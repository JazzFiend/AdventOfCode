
class ParseRightOfGearCommand extends ParseCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 >= schematic.head.length - 1) { return 0 }

    SchematicNumberParser.parseNumberToRight(schematic(gearLocation._2), gearLocation._1 + 1)
  }
}
