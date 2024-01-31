import org.scalatest.funspec.AnyFunSpec

class ScratchCardTextParserTest extends AnyFunSpec {
  it("No scratch cards should return an empty list") {
    assert(ScratchCardTextParser.parse(List.empty) == List.empty)
  }
}
