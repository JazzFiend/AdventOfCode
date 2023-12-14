object GameEvaluator {
  def scorePossibleGames(gameStrings: List[String], maxRed: Int, maxGreen: Int, maxBlue: Int): Int = {
    val games = GameTranslator.translateGames(gameStrings)
    games.map(game => {
      if (isPossible(maxRed, maxGreen, maxBlue, game)) {
        game.id
      } else {
        0
      }
    }).sum
  }

  private def isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int, game: Game): Boolean = {
    game.hints.forall(hint => {
      hint.red <= maxRed && hint.green <= maxGreen && hint.blue <= maxBlue
    })
  }
}
