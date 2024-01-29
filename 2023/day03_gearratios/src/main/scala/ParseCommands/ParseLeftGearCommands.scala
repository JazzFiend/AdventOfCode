package ParseCommands

class ParseLeftGearCommands extends ParseGearCommandCollection {
  override def executeAll(schematic: List[String], gearLocation: (Int, Int)): List[Int] = {
    List(ParseGearLeftCommand().execute(schematic, gearLocation))
  }
}
