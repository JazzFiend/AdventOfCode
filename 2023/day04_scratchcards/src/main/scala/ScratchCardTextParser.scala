object ScratchCardTextParser {
  def parse(text: String): Game = {
    if (text.isEmpty) { return Game(List.empty, List.empty) }

    val allGameNumbers = extractNumbers(text)
    Game(parseNumbers(allGameNumbers.head), parseNumbers(allGameNumbers.last))
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

  private def parseNumbers(numbers: String): List[Int] = {
    val parsedNumberStrings = numbers.trim.split(" ").toList
    parsedNumberStrings.map(n => n.toInt)
  }
}
