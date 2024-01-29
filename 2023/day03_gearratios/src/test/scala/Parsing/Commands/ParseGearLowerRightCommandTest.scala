package Parsing.Commands

import org.scalatest.funspec.AnyFunSpec

class ParseGearLowerRightCommandTest extends AnyFunSpec {
  it("A valid lower right number should be returned as an Int") {
    val schematic = List(".*..", "..22")
    val command = ParseGearLowerRightCommand()
    assert(command.execute(schematic, (1, 0)) == 22)
  }

  // I'm like 98% sure that none of the part numbers will just be 0. If that's false, then this test
  // needs to change.
  it("No number should return 0") {
    val schematic = List("..*.", "....")
    val command = ParseGearLowerRightCommand()
    assert(command.execute(schematic, (2, 0)) == 0)
  }

  it("A gear on the right side should return 0") {
    val schematic = List("...*", "....")
    val command = ParseGearLowerRightCommand()
    assert(command.execute(schematic, (3, 0)) == 0)
  }

  it("A gear on the bottom should return 0") {
    val schematic = List("....", ".*..")
    val command = ParseGearLowerRightCommand()
    assert(command.execute(schematic, (1, 1)) == 0)
  }
}
