import org.scalatest.funspec.AnyFunSpec

class ScratchCardTextParserTest extends AnyFunSpec {
  it("No scratch cards should return an empty pair") {
    assert(ScratchCardTextParser.parse("") == Game(List.empty, List.empty))
  }

  it("A valid scratch card with no numbers should throw") {
    val gameText = "Card 3: |"
    val caught = intercept[RuntimeException] {
      ScratchCardTextParser.parse(gameText)
    }
    assert(caught.getMessage == "Game does not have enough numbers")
  }

  it("A game with one winning number should throw") {
    val gameText = "Card 3: 12 |"
    val caught = intercept[RuntimeException] {
      ScratchCardTextParser.parse(gameText)
    }
    assert(caught.getMessage == "Game does not have enough numbers")
  }

  it("A game with one winning number and one player number should parse") {
    val gameText = "Card 18: 3 | 97"
    val result = Game(List(3), List(97))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }
}
