object ScratchCardTextParser {
  def parse(text: String): (List[Int], List[Int]) = {
    if (text.isEmpty) { return (null, null) }

    val allGameNumbers = extractNumbers(text)
    (parseWinningNumbers(allGameNumbers.head), parsePlayerNumbers(allGameNumbers.last))
  }

  private def extractNumbers(text: String): List[String] = {
    val allGameNumbers = text.split(":")(1).split('|')
    checkNumberFormatting(allGameNumbers)
    allGameNumbers.toList
  }

  private def checkNumberFormatting(allGameNumbers: Array[String]): Unit = {
    if (allGameNumbers.length < 2) {
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
