object ScratchCardTextParser {
  def parse(text: String): Game = {
    if (text.isEmpty) { return Game(0, List.empty, List.empty) }

    val allGameNumbers = extractNumbers(text)
    Game(allGameNumbers.head.toInt, parseNumbers(allGameNumbers(1)), parseNumbers(allGameNumbers.last))
  }

  private def extractNumbers(text: String): List[String] = {
    val cardNumber = text.split(":").head.split(" ").last
    val winningAndPlayerNumbers = text.split(":")(1).split('|')
    checkNumberFormatting(winningAndPlayerNumbers)
    List(cardNumber, winningAndPlayerNumbers.head, winningAndPlayerNumbers.last)
  }

  private def checkNumberFormatting(allGameNumbers: Array[String]): Unit = {
    val validNumberLists = allGameNumbers.filter(numbers => numbers.trim.nonEmpty)
    if (validNumberLists.length < 2) {
      throw RuntimeException("Game does not have enough numbers")
    }
  }

  private def parseNumbers(numbers: String): List[Int] = {
    numbers.trim.split(" ").toList
      .filterNot(n => n.isEmpty)
      .map(n => n.toInt)
  }
}
