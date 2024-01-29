package Parsing.Commands

import org.scalatest.funspec.AnyFunSpec

class ParseGearUpCommandTest extends AnyFunSpec {
  it("No number should return 0") {
    val schematic = List("....", ".*..")
    val command = ParseGearUpCommand()
    assert(command.execute(schematic, (1, 1)) == 0)
  }

  it("A gear on the top should return 0") {
    val schematic = List(".*..", "....")
    val command = ParseGearUpCommand()
    assert(command.execute(schematic, (1, 0)) == 0)
  }

  it("A valid one digit number on top should be returned as an Int") {
    val schematic = List("..8.", "..*.")
    val command = ParseGearUpCommand()
    assert(command.execute(schematic, (2, 1)) == 8)
  }

  it("A number on top that extends to the right should be returned as an Int") {
    val schematic = List(".4820", ".*...")
    val command = ParseGearUpCommand()
    assert(command.execute(schematic, (1, 1)) == 4820)
  }

  it("A number on top that extends to the left should be returned as an Int") {
    val schematic = List(".5368", "....*")
    val command = ParseGearUpCommand()
    assert(command.execute(schematic, (4, 1)) == 5368)
  }

  it("A number on top that extends to the left and right should be returned as an Int") {
    val schematic = List("24582", "..*..")
    val command = ParseGearUpCommand()
    assert(command.execute(schematic, (2, 1)) == 24582)
  }
}
