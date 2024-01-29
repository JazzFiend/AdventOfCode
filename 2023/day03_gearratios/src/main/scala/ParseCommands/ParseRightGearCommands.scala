package ParseCommands

class ParseRightGearCommands extends ParseGearCommandCollection {
  override def executeAll(schematic: List[String], gearLocation: (Int, Int)): List[Int] = {
    List(ParseGearRightCommand().execute(schematic, gearLocation))
  }
}