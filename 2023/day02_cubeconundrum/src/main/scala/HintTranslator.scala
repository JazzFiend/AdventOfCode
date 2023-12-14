object HintTranslator {
  def extractHints(gameLine: String): List[Hint] = {
    val hintStrings = gameLine.split(":").last.split(";")
    val listOfHints = hintStrings.map((hintString) => multipleHintsMapFunction(hintString))
    listOfHints.filterNot((hint) => hint == new Hint(0, 0, 0)).toList
  }

  private def multipleHintsMapFunction(hint: String):Hint = {
    val hintColors = hint.split(",")
    val redCount = hintColors.map(hintColor => colorCounter(hintColor, "red")).sum
    val greenCount = hintColors.map(hintColor => colorCounter(hintColor, "green")).sum
    val blueCount = hintColors.map(hintColor => colorCounter(hintColor, "blue")).sum
    new Hint(redCount, greenCount, blueCount)
  }

  private def colorCounter(hintColor: String, color: String): Int = {
    val hintColorTokenized = hintColor.split(" ")
    val count = hintColorTokenized(1)
    val thisColor = hintColorTokenized.last
    if (color == thisColor) {
      count.toInt
    } else {
      0
    }
  }
}
