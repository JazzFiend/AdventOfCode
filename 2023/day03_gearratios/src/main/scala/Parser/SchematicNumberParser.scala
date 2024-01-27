package Parser

object SchematicNumberParser {
  def parseNumber(schematicLine: String, startPos: Int): Int = {
    val lengthLeft = calculateNumberLengthFromEnd(schematicLine, startPos, 0)
    parseNumberFromStart(schematicLine, startPos - lengthLeft + 1)
  }

  def calculateNumberCoordinates(line: String, startLocation: Int, y: Int): Set[(Int, Int)] = {
    val numberLength = calculateNumberLengthFromStart(line, startLocation, 0)
    val xRange = Range.inclusive(startLocation, startLocation + numberLength - 1)
    xRange.map(x => (x, y)).toSet
  }

  private def parseNumberFromStart(schematicLine: String, numberStartX: Int): Int = {
    val rightSize = calculateNumberLengthFromStart(schematicLine, numberStartX, 0)
    var rightNumber = 0
    if (rightSize > 0) {
      rightNumber = schematicLine.substring(numberStartX, numberStartX + rightSize).toInt
    }
    rightNumber
  }

  private def calculateNumberLengthFromEnd(schematicLine: String, xPos: Int, lengthCounter: Int):Int = {
    if (schematicLine.charAt(xPos).isDigit && xPos == 0) {
      return lengthCounter + 1
    }
    if (schematicLine.charAt(xPos).isDigit) {
      return calculateNumberLengthFromEnd(schematicLine, xPos - 1, lengthCounter + 1)
    }
    lengthCounter
  }

  private def calculateNumberLengthFromStart(schematicLine: String, xPos: Int, lengthCounter: Int): Int = {
    if (schematicLine.charAt(xPos).isDigit && xPos >= schematicLine.length - 1) {
      return lengthCounter + 1
    }
    if (schematicLine.charAt(xPos).isDigit) {
      return calculateNumberLengthFromStart(schematicLine, xPos + 1, lengthCounter + 1)
    }
    lengthCounter
  }
}
