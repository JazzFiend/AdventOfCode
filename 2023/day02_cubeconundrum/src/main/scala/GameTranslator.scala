object GameTranslator {
  def translateGames(gameLines: List[String]) : List[Game] = {
    gameLines.map(gameLine => translateGame(gameLine))
  }

  private def translateGame(gameLine: String): Game = {
    if (gameLine.isEmpty) {
      throw new IllegalArgumentException("Game not formatted correctly")
    }

    val id = extractGameId(gameLine)
    val hints = HintTranslator.extractHints(gameLine)
    new Game(id, hints)
  }

  private def extractGameId(gameLine: String): Int = {
    val idWithColon = gameLine.split(" ")(1)
    idWithColon.substring(0, idWithColon.length - 1).toInt
  }
}
