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

  it("A game with no player numbers should throw") {
    val gameText = "Card 3: 12 |"
    val caught = intercept[RuntimeException] {
      ScratchCardTextParser.parse(gameText)
    }
    assert(caught.getMessage == "Game does not have enough numbers")
  }

  it("A game with no winning numbers should throw") {
    val gameText = "Card 3: | 78"
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

  it("A game with many winning numbers should parse correctly") {
    val gameText = "Card 18: 63 59 93 12 | 76"
    val result = Game(List(63, 59, 93, 12), List(76))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }
}
