object ScratchCardTextParser {
  def parse(text: List[String]): List[(List[Int], List[Int])] = {
    if(text.isEmpty) { return List.empty }

    val gameNumbers = text.head.split(":")(1)
    val winningNumbers = gameNumbers.split('|').head.trim
    if(winningNumbers.nonEmpty) {
      return List((List(winningNumbers.toInt), List.empty))
    }
    List((List.empty, List.empty))
  }
}
