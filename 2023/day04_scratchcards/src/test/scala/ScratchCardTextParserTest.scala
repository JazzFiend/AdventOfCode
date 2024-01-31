import org.scalatest.funspec.AnyFunSpec

class ScratchCardTextParserTest extends AnyFunSpec {
  it("No scratch cards should return an empty list") {
    assert(ScratchCardTextParser.parse(List.empty) == List.empty)
  }

  it("A valid scratch card with no numbers should return an empty game") {
    val gameText = List("Card 3: |")
    assert(ScratchCardTextParser.parse(gameText) == List((List.empty, List.empty)))
  }

  it("A game with one winning number") {
    val gameText = List("Card 3: 12 |")
    val result = List((List(12), List.empty))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }
}
