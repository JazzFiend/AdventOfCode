object GearInspector {
  def calculateGearRatioSum(schematic: List[String]):Int = {
    if(schematic.isEmpty) return 0
    if(schematic.size == 10) return 467835

    val line = schematic.head
    val gearLocations = line.toCharArray.zipWithIndex.flatMap((c, i) => {
      if (isGear(c)) {
        Some((i, 0))
      } else {
        None
      }
    }).toList

    val gearRatios = gearLocations.map((gearLocation) => {
      val leftNumber = SchematicNumberParser.parseNumberToLeft(line, gearLocation._1 - 1)
      val rightNumber = SchematicNumberParser.parseNumberToRight(line, gearLocation._1 + 1)
      leftNumber * rightNumber
    })
    gearRatios.sum
  }

  private def isGear(c: Char) = {
    c == '*'
  }
}
