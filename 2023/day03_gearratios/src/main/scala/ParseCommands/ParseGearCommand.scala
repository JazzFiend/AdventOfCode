package ParseCommands

abstract class ParseGearCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int
}
