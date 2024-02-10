import org.scalatest.funspec.AnyFunSpec

// ***** TODO: PLACEHOLDERS FOR ALL GAMES

class ScratchCardTextParserTest extends AnyFunSpec {
  it("No scratch cards should return an empty pair") {
    assert(ScratchCardTextParser.parse("") == Game(0, List.empty, List.empty))
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
    val result = Game(1, List(3), List(97))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }

  it("A game with many winning numbers should parse correctly") {
    val gameText = "Card 18: 63 59 93 12 | 76"
    val result = Game(1, List(63, 59, 93, 12), List(76))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }

  it("A game with many player numbers should parse correctly") {
    val gameText = "Card 18: 48 983 | 2 94 85 12 467"
    val result = Game(1, List(48, 983), List(2, 94, 85, 12, 467))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }

  it("Handle multiple spaces between numbers") {
    val gameText = "Card 18:  328  46  3  |  9  32  111 "
    val result = Game(1, List(328, 46, 3), List(9, 32, 111))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }
}
