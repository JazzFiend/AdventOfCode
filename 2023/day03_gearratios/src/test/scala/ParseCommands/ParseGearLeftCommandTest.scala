package ParseCommands

import ParseCommands.ParseGearLeftCommand
import org.scalatest.funspec.AnyFunSpec

class ParseGearLeftCommandTest extends AnyFunSpec {
  it("A gear on the left side should return 0") {
    val schematic = List("*....")
    val command = ParseGearLeftCommand()
    assert(command.execute(schematic, (0, 0)) == 0)
  }
}