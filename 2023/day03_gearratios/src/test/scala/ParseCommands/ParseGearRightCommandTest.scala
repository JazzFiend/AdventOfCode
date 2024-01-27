package ParseCommands

import ParseCommands.ParseGearRightCommand
import org.scalatest.funspec.AnyFunSpec

class ParseGearRightCommandTest extends AnyFunSpec {
  it("A gear on the right side should return 0") {
    val schematic = List("...*")
    val command = ParseGearRightCommand()
    assert(command.execute(schematic, (3, 0)) == 0)
  }
}
