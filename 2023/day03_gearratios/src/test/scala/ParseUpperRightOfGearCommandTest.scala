import org.scalatest.funspec.AnyFunSpec

class ParseUpperRightOfGearCommandTest extends AnyFunSpec {
  it("A valid upper right number should be returned as an Int") {
    val schematic = List(".69.", "*...")
    val command = ParseUpperRightOfGearCommand()
    assert(command.execute(schematic, (0, 1)) == 69)
  }

  // I'm like 98% sure that none of the part numbers will just be 0. If that's false, then this test
  // needs to change.
  it("No number should return 0") {
    val schematic = List("....", "*...")
    val command = ParseUpperRightOfGearCommand()
    assert(command.execute(schematic, (0, 1)) == 0)
  }

  it("A number on the right side should return 0") {
    val schematic = List("....", "...*")
    val command = ParseUpperRightOfGearCommand()
    assert(command.execute(schematic, (3, 1)) == 0)
  }

  it("A number on the top should return 0") {
    val schematic = List("....", ".*..")
    val command = ParseUpperRightOfGearCommand()
    assert(command.execute(schematic, (1, 0)) == 0)
  }
}
