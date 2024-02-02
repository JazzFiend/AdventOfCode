import org.scalatest.funspec.AnyFunSpec

class ScratchCardTextParserTest extends AnyFunSpec {
  it("No scratch cards should return an empty list") {
    assert(ScratchCardTextParser.parse(List.empty) == List.empty)
  }

  it("A valid scratch card with no numbers should return an empty game") {
    val gameText = List("Card 3: |")
    val caught = intercept[RuntimeException] {
      ScratchCardTextParser.parse(gameText)
    }
  }

  it("A game with one winning number") {
    val gameText = List("Card 3: 12 |")
    val caught = intercept[RuntimeException] {
      ScratchCardTextParser.parse(gameText)
    }
  }

  it("A game with one winning number and one player number") {
    val gameText = List("Card 18: 3 | 97")
    val result = List((List(3), List(97)))
    assert(ScratchCardTextParser.parse(gameText) == result)
  }
}
