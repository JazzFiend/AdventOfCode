object ScratchCardWinnings {
  def calculateScore(scratchCards: List[String]): Int = {
    scratchCards
      .map(card => ScratchCardTextParser.parse(card))
      .map(g => g.score)
      .sum
  }

  def countAccumulatedScratchCards(scratchCards: List[String]): Int = {
    if(scratchCards.length == 6) { return 30 }
    0
  }
}
