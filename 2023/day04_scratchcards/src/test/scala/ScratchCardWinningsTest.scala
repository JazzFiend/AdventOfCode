import org.scalatest.funspec.AnyFunSpec

class ScratchCardWinningsTest extends AnyFunSpec {
  describe("Scoring") {
    it("Acceptance Test") {
      val scratchCards = List(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
      )
      assert(ScratchCardWinnings.calculateScore(scratchCards) == 13)
    }

    it("Puzzle 1") {
      val scratchCards = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
      assert(ScratchCardWinnings.calculateScore(scratchCards) == 18653)
    }
  }

  describe("Card Counting") {
    it("No cards should return a card count of zero") {
      assert(ScratchCardWinnings.countAccumulatedScratchCards(List.empty) == 0)
    }

    it("One card with no winnings should return one card") {
      val scratchCards = List("Card 3:  1 2 3 4 5 | 11 12 13 14 15")
      assert(ScratchCardWinnings.countAccumulatedScratchCards(scratchCards) == 1)
    }

    it("One card with winning should still return one card") {
      val scratchCards = List("Card 3:  1 2 3 4 5 | 1 2 3 4 5")
      assert(ScratchCardWinnings.countAccumulatedScratchCards(scratchCards) == 1)
    }

    it("Many cards with one winning number") {
      val scratchCards = List(
        "Card 3:  1 2 3 4 5 | 1 12 13 14 15",
        "Card 5:  1 2 3 4 5 | 11 12 13 14 15",
      )
      assert(ScratchCardWinnings.countAccumulatedScratchCards(scratchCards) == 3)
    }

    it("One winning card with several wins") {
      val scratchCards = List(
        "Card 3:  1 2 3 4 5 | 1 2 13 14 15",
        "Card 5:  1 2 3 4 5 | 11 12 13 14 15",
        "Card 8:  1 2 3 4 5 | 11 12 13 14 15",
      )
      assert(ScratchCardWinnings.countAccumulatedScratchCards(scratchCards) == 5)
    }

    it("Acceptance Test") {
      val scratchCards = List(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
      )
      assert(ScratchCardWinnings.countAccumulatedScratchCards(scratchCards) == 30)
    }

    it("Puzzle 2") {
      val scratchCards = io.Source.fromFile("src/test/scala/input.txt").getLines.toList
      assert(ScratchCardWinnings.countAccumulatedScratchCards(scratchCards) == 5921508)
    }
  }
}
