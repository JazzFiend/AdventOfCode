package Parsing.Commands

import org.scalatest.funspec.AnyFunSpec

class ParseGearLowerLeftCommandTest extends AnyFunSpec {
  it("A valid lower left number should be returned as an Int") {
    val schematic = List(".*..", "2...")
    val command = ParseGearLowerLeftCommand()
    assert(command.execute(schematic, (1, 0)) == 2)
  }

  // I'm like 98% sure that none of the part numbers will just be 0. If that's false, then this test
  // needs to change.
  it("No number should return 0") {
    val schematic = List(".*..", "....")
    val command = ParseGearLowerLeftCommand()
    assert(command.execute(schematic, (1, 0)) == 0)
  }

  it("A gear on the left side should return 0") {
    val schematic = List("*...", "....")
    val command = ParseGearLowerLeftCommand()
    assert(command.execute(schematic, (0, 0)) == 0)
  }

  it("A gear on the bottom should return 0") {
    val schematic = List("....", "..*.")
    val command = ParseGearLowerLeftCommand()
    assert(command.execute(schematic, (2, 1)) == 0)
  }
}
