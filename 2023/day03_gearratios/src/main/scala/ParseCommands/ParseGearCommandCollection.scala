package ParseCommands

abstract class ParseGearCommandCollection {
  def executeAll(schematic: List[String], gearLocation: (Int, Int)): List[Int]
}
