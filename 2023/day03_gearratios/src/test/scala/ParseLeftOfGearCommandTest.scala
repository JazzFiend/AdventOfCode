import org.scalatest.funspec.AnyFunSpec

class ParseLeftOfGearCommandTest extends AnyFunSpec {
  it("A number on the left side should return 0") {
    val schematic = List("*....")
    val command = ParseLeftOfGearCommand()
    assert(command.execute(schematic, (0, 0)) == 0)
  }
}