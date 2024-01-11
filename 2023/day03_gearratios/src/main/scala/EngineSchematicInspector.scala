object EngineSchematicInspector {
  def calculatePartSum(schematic: List[String]): Int = {
    if (schematic.isEmpty) { return 0 }

    val schematicNumbers = extractAllSchematicNumbers(schematic)
    val numbersNearSymbol = schematicNumbers.filter(schematicNumber => isSymbolNearby(schematicNumber, schematic))
    val allValues = numbersNearSymbol.toList.map(schematicNumber => schematicNumber.value)
    allValues.sum
  }

  private def extractAllSchematicNumbers(schematic: List[String]) = {
    schematic.zipWithIndex.flatMap((line, j) => {
      val numbers = line.toCharArray.zipWithIndex.flatMap((c, i) => {
        if (isFirstDigit(line, c, i)) {
          val numberLength = findLengthOfNumber(line, i, 0)
          val xRange = Range.inclusive(i, i + numberLength - 1)
          val numberValue = extractValueOfNumber(line, i, xRange.size)
          val coordinates = xRange.map(x => (x, j)).toSet
          Some(SchematicNumber(numberValue, coordinates))
        } else {
          None
        }
      })
      Some(numbers.toSet)
    }).reduce((a, b) => a ++ b)
  }

  private def isFirstDigit(line: String, c: Char, pos: Int) = {
    c.isDigit && (pos == 0 || (pos > 0 && !line.charAt(pos - 1).isDigit))
  }

  private def findLengthOfNumber(line: String, i: Int, counter: Int): Int = {
    if(line.charAt(i).isDigit && i >= line.length - 1) {
      return counter + 1
    }
    if(line.charAt(i).isDigit) {
      return findLengthOfNumber(line, i + 1, counter + 1)
    }
    counter
  }

  private def extractValueOfNumber(line: String, i: Int, numberLength: Int): Int = {
    line.substring(i, i + numberLength).toInt
  }

  private def isSymbolNearby(schematicNumber: SchematicNumber, schematic: List[String]): Boolean = {
    val xStart = schematicNumber.coordinates.head._1
    val xEnd = schematicNumber.coordinates.last._1
    val y = schematicNumber.coordinates.head._2
    val xRange = Range.inclusive(xStart - 1, xEnd + 1)
    val yRange = Range.inclusive(y - 1, y + 1)

    yRange.map(y => {
      xRange.map(x => {
        if(isInSchematicRange(schematic, y, x) && isSymbol(schematic(y).charAt(x))) {
          true
        } else {
          false
        }
      })
    }).exists(_.exists(_ == true))
  }

  private def isInSchematicRange(schematic: List[String], y: Int, x: Int) = {
    x >= 0 && x < schematic.head.length && y >= 0 && y < schematic.length
  }

  private def isSymbol(c: Char): Boolean = {
    !c.isLetterOrDigit && c != '.'
  }
}
