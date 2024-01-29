package Parsing.Commands

import Parsing.Commands.ParseGearUpperRightCommand
import org.scalatest.funspec.AnyFunSpec

class ParseGearUpperRightCommandTest extends AnyFunSpec {
  it("A valid upper right number should be returned as an Int") {
    val schematic = List(".69.", "*...")
    val command = ParseGearUpperRightCommand()
    assert(command.execute(schematic, (0, 1)) == 69)
  }

  // I'm like 98% sure that none of the part numbers will just be 0. If that's false, then this test
  // needs to change.
  it("No number should return 0") {
    val schematic = List("....", "*...")
    val command = ParseGearUpperRightCommand()
    assert(command.execute(schematic, (0, 1)) == 0)
  }

  it("A gear on the right side should return 0") {
    val schematic = List("....", "...*")
    val command = ParseGearUpperRightCommand()
    assert(command.execute(schematic, (3, 1)) == 0)
  }

  it("A gear on the top should return 0") {
    val schematic = List("....", ".*..")
    val command = ParseGearUpperRightCommand()
    assert(command.execute(schematic, (1, 0)) == 0)
  }
}
