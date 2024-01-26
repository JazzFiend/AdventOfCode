class ParseUpperRightOfGearCommand extends ParseCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int = {
    if(gearLocation._1 == schematic.head.length - 1 || gearLocation._2 == 0) { return 0 }

    val startingPoint = (gearLocation._1 + 1, gearLocation._2 - 1)
    SchematicNumberParser.parseNumberToRight(schematic(startingPoint._2), startingPoint._1)
  }
}
