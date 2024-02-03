object ScratchCardTextParser {
  def parse(text: String): Game = {
    if (text.isEmpty) { return Game(List.empty, List.empty) }

    val allGameNumbers = extractNumbers(text)
    Game(parseWinningNumbers(allGameNumbers.head), parsePlayerNumbers(allGameNumbers.last))
  }

  private def extractNumbers(text: String): List[String] = {
    val allGameNumbers = text.split(":")(1).split('|')
    checkNumberFormatting(allGameNumbers)
    allGameNumbers.toList
  }

  private def checkNumberFormatting(allGameNumbers: Array[String]): Unit = {
    val validNumberLists = allGameNumbers.filter(numbers => numbers.trim.nonEmpty)
    if (validNumberLists.length < 2) {
      throw RuntimeException("Game does not have enough numbers")
    }
  }

  private def parseWinningNumbers(winningNumbers: String): List[Int] = {
    List(winningNumbers.trim.toInt)
  }

  private def parsePlayerNumbers(playerNumbers: String): List[Int] = {
    List(playerNumbers.trim.toInt)
  }
}
