object MinimumCubeCalculator {
  def calculateMinimumScore(games: List[String]): Int = {
//    if (games.size == 5) { return 2286 }

    val gamesTranslated = GameTranslator.translateGames(games)
    val scoredGames = gamesTranslated.map(game => calculateScore(game))
    scoredGames.sum
  }

  private def calculateScore(game: Game): Int = {
    val minimumReds = if(calculateMinimumColor(game, "red") == 0) 1 else calculateMinimumColor(game, "red")
    val minimumGreens = if(calculateMinimumColor(game, "green") == 0) 1 else calculateMinimumColor(game, "green")
    val minimumBlues = if(calculateMinimumColor(game, "blue") == 0) 1 else calculateMinimumColor(game, "blue")
    minimumBlues * minimumGreens * minimumReds
  }

  private def calculateMinimumColor(game: Game, color: String): Int = {
    val justOneColor = reduceToOneColor(game, color)
    val filterEmpties = justOneColor.filterNot(color => color == 0)
    if(filterEmpties.isEmpty) {
      0
    } else {
      filterEmpties.max
    }
  }

  private def reduceToOneColor(game:Game, color: String): List[Int] = {
    color match {
      case "red" => game.hints.map(hint => hint.red)
      case "green" => game.hints.map(hint => hint.green)
      case "blue" => game.hints.map(hint => hint.blue)
      case _ => throw new RuntimeException("Incorrect color seen")
    }
  }
}
