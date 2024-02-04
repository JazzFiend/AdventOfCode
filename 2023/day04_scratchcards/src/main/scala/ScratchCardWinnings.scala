object ScratchCardWinnings {
  def calculateScore(scratchCards: List[String]): Int = {
    scratchCards
      .map(card => ScratchCardTextParser.parse(card))
      .map(g => g.score)
      .sum
  }
}
