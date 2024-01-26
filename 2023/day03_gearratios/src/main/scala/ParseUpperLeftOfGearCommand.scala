class ParseUpperLeftOfGearCommand extends ParseCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 == 0 || gearLocation._2 == 0) { return 0 }

    val startingPoint = (gearLocation._1 - 1, gearLocation._2 - 1)
    SchematicNumberParser.parseNumberToLeft(schematic(startingPoint._2), startingPoint._1)
  }
}
