object ScratchCardWinnings {
  def calculateScore(scratchCards: List[String]): Int = {
    val games = scratchCards.map(card => ScratchCardTextParser.parse(card))
    13
  }

  // First we need to turn the list of strings into Game objects. This might just be pairs of winning numbers and player numbers, or an actual object. (DONE)
  // Then we need to score each game. We can just map that.
  // Finally, we sum everything up and return it. Just a .sum
}
