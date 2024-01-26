
class ParseLeftOfGearCommand extends ParseCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 <= 0) { return 0 }

    SchematicNumberParser.parseNumberToLeft(schematic(gearLocation._2), gearLocation._1 - 1)
  }
}
