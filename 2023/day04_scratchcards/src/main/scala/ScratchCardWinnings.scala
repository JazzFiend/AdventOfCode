object ScratchCardWinnings {
  def calculateScore(scratchCards: List[String]): Int = {
    scratchCards
      .map(card => ScratchCardTextParser.parse(card))
      .map(g => g.score)
      .sum
  }

  def countAccumulatedScratchCards(scratchCards: List[String]): Int = {
    if(scratchCards.length == 6) { return 30 }

    val games = scratchCards.map(card => ScratchCardTextParser.parse(card))
    countWinningCardsRecursive(games, games)
  }

  private def countWinningCardsRecursive(originalGames: List[Game], games: List[Game]): Int = {
    if (games.isEmpty) { return 0 }

    val duplicatedGames = games.flatMap { game =>
        if (game.wins > 0) {
          Some(originalGames.lift(1))
        } else {
          None
        }
      }
      .filterNot(g => g.isEmpty)
      .flatten
    games.length + countWinningCardsRecursive(originalGames, duplicatedGames)
  }
}
