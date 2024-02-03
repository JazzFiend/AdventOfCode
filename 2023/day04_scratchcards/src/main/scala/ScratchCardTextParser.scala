object ScratchCardTextParser {
  def parse(text: List[String]): List[(List[Int], List[Int])] = {
    if(text.isEmpty) { return List.empty }

    val allGameNumbers = extractNumbers(text)
    List((parseWinningNumbers(allGameNumbers.head), parsePlayerNumbers(allGameNumbers.last)))
  }

  private def extractNumbers(text: List[String]): List[String] = {
    val allGameNumbers = text.head.split(":")(1).split('|')
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
