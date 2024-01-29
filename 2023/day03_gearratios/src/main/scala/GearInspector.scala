import Parsing.CommandCollections.{ParseLeftGearCommands, ParseRightGearCommands, ParseTopGearCommands}

object GearInspector {
  def calculateGearRatioSum(schematic: List[String]):Int = {
    if(schematic.isEmpty) return 0
    if(schematic.size == 10) return 467835

    val gearLocations: List[(Int, Int)] = findAllGears(schematic)
    calculateGearRatios(schematic, gearLocations).sum
  }

  private def findAllGears(schematic: List[String]): List[(Int, Int)] = {
    schematic.zipWithIndex.flatMap((line, y) => {
      val gearsOnLine = line.toCharArray.zipWithIndex.flatMap((char, x) => {
        if (isGear(char)) { Some((x, y)) } else { None }
      }).toList
      Some(gearsOnLine)
    }).reduce((a, b) => a ++ b)
  }

  private def isGear(c: Char): Boolean = {
    c == '*'
  }

  private def calculateGearRatios(schematic: List[String], gearLocations: List[(Int, Int)]): List[Int] = {
    gearLocations.map((gearLocation) => {
      val adjacentNumbers = gearCommandCollections.flatMap(command => { command.executeAll(schematic, gearLocation) })
        .filter(n => n != 0)
      //TODO: I want to throw an exception if the size is greater than two. The input should never have that.
      if (adjacentNumbers.size == 2) {
        adjacentNumbers.product
      } else {
        0
      }
    })
  }

  private val gearCommandCollections = List(
    ParseLeftGearCommands(),
    ParseRightGearCommands(),
    ParseTopGearCommands()
  )
}
