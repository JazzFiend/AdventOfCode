package Parser

object SchematicNumberParser {
  def parseNumberToLeft(schematicLine: String, numberEndX: Int): Int = {
    val leftSize = calculateNumberLengthLeft(schematicLine, numberEndX, 0)
    var leftNumber = 0
    if (leftSize > 0) {
      leftNumber = schematicLine.substring(numberEndX - leftSize + 1, numberEndX + 1).toInt
    }
    leftNumber
  }

  def parseNumberToRight(schematicLine: String, numberStartX: Int): Int = {
    val rightSize = calculateNumberLengthRight(schematicLine, numberStartX, 0)
    var rightNumber = 0
    if (rightSize > 0) {
      rightNumber = schematicLine.substring(numberStartX, numberStartX + rightSize).toInt
    }
    rightNumber
  }

  def calculateNumberCoordinates(line: String, startLocation: Int, y: Int): Set[(Int, Int)] = {
    val numberLength = calculateNumberLengthRight(line, startLocation, 0)
    val xRange = Range.inclusive(startLocation, startLocation + numberLength - 1)
    xRange.map(x => (x, y)).toSet
  }

  private def calculateNumberLengthLeft(schematicLine: String, xPos: Int, lengthCounter: Int):Int = {
    if (schematicLine.charAt(xPos).isDigit && xPos == 0) {
      return lengthCounter + 1
    }
    if (schematicLine.charAt(xPos).isDigit) {
      return calculateNumberLengthLeft(schematicLine, xPos - 1, lengthCounter + 1)
    }
    lengthCounter
  }

  private def calculateNumberLengthRight(schematicLine: String, xPos: Int, lengthCounter: Int): Int = {
    if (schematicLine.charAt(xPos).isDigit && xPos >= schematicLine.length - 1) {
      return lengthCounter + 1
    }
    if (schematicLine.charAt(xPos).isDigit) {
      return calculateNumberLengthRight(schematicLine, xPos + 1, lengthCounter + 1)
    }
    lengthCounter
  }

  def parseNumberMiddle(schematicLine: String, startPos: Int): Int = {
    val lengthLeft = calculateNumberLengthLeft(schematicLine, startPos, 0)
    parseNumberToRight(schematicLine, startPos - lengthLeft + 1)
  }
}
