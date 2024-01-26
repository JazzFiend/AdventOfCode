import org.scalatest.funspec.AnyFunSpec

class ParseRightOfGearCommandTest extends AnyFunSpec {
  it("A number on the right side should return 0") {
    val schematic = List("...*")
    val command = ParseRightOfGearCommand()
    assert(command.execute(schematic, (3, 0)) == 0)
  }
}
