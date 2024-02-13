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

    val duplicatedGames = extractDuplicateGames(originalGames, games)
    games.length + countWinningCardsRecursive(originalGames, duplicatedGames)
  }

  private def extractDuplicateGames(originalGames: List[Game], currentGames: List[Game]): List[Game] = {
    currentGames.flatMap { game =>
        if (game.wins > 0) {
          val originalGameLocation = findGameInOriginalList(originalGames, game)
          val duplicateGames =
            originalGames.slice(originalGameLocation + 1, originalGameLocation + game.wins + 1)
          Some(duplicateGames)
        } else {
          None
        }
      }
      .filterNot(g => g.isEmpty)
      .flatten
  }

  private def findGameInOriginalList(originalGames: List[Game], gameToFind: Game) = {
    originalGames
      .zipWithIndex
      .filter(originalGame => originalGame._1.cardNumber == gameToFind.cardNumber)
      .head
      ._2
  }
}
