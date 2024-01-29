package Parsing.Commands

import org.scalatest.funspec.AnyFunSpec

class ParseGearDownCommandTest extends AnyFunSpec {
  it("No number should return 0") {
    val schematic = List("*...", "....")
    val command = ParseGearDownCommand()
    assert(command.execute(schematic, (0, 0)) == 0)
  }

  it("A gear on the bottom should return 0") {
    val schematic = List("....", "...*")
    val command = ParseGearDownCommand()
    assert(command.execute(schematic, (1, 0)) == 0)
  }

  it("A valid one digit number on the bottom should be returned as an Int") {
    val schematic = List(".*..", ".5..")
    val command = ParseGearDownCommand()
    assert(command.execute(schematic, (1, 0)) == 5)
  }

  it("A number on the bottom that extends to the right should be returned as an Int") {
    val schematic = List("..*..", "..523")
    val command = ParseGearDownCommand()
    assert(command.execute(schematic, (4, 0)) == 523)
  }

  it("A number on the bottom that extends to the left should be returned as an Int") {
    val schematic = List(".*...", "23...")
    val command = ParseGearDownCommand()
    assert(command.execute(schematic, (1, 0)) == 23)
  }

  it("A number on the bottom that extends to the left and right should be returned as an Int") {
    val schematic = List("..*..", "89248")
    val command = ParseGearDownCommand()
    assert(command.execute(schematic, (2, 0)) == 89248)
  }
}
