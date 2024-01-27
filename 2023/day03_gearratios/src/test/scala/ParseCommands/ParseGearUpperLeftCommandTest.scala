package ParseCommands

import org.scalatest.funspec.AnyFunSpec

class ParseGearUpperLeftCommandTest extends AnyFunSpec {
  it("A valid upper left number should be returned as an Int") {
    val schematic = List("346.", "...*")
    val command = ParseGearUpperLeftCommand()
    assert(command.execute(schematic, (3, 1)) == 346)
  }

  // I'm like 98% sure that none of the part numbers will just be 0. If that's false, then this test
  // needs to change.
  it("No number should return 0") {
    val schematic = List("....", "...*")
    val command = ParseGearUpperLeftCommand()
    assert(command.execute(schematic, (3, 1)) == 0)
  }

  it("A gear on the left side should return 0") {
    val schematic = List("....", "*...")
    val command = ParseGearUpperLeftCommand()
    assert(command.execute(schematic, (0, 1)) == 0)
  }

  it("A gear on the top should return 0") {
    val schematic = List("..*.", "....")
    val command = ParseGearUpperLeftCommand()
    assert(command.execute(schematic, (2, 0)) == 0)
  }
}
