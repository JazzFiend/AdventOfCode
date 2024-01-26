abstract class ParseCommand {
  def execute(schematic: List[String], gearLocation: (Int, Int)): Int
}
