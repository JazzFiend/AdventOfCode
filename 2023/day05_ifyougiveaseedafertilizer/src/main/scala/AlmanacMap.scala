class AlmanacMap(val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(numbersToMap: List[Long]):List[Long] = {
    numbersToMap.map(inputNumber => {
      val mapIndexToUse = mapRanges.map(mapRange => mapRange.isInRange(inputNumber))
        .zipWithIndex.filter((value, index) => value)
        .map((value, index) => index)
      if (mapIndexToUse.isEmpty) {
        inputNumber
      } else {
        calculateMappedValue(inputNumber, mapIndexToUse.head)
      }
    })
  }

  private def calculateMappedValue(inputNumber: Long, mapRangeIndex: Int): Long = {
    val rangeToUse = mapRanges(mapRangeIndex)
    rangeToUse.destinationRangeStart + (inputNumber - rangeToUse.sourceRangeStart)
  }

  def mapSourceValuesAsRanges(sourceRanges: Set[(Long, Long)]): Set[(Long, Long)] = {
    sourceRanges.flatMap(sourceRange => {
      val overlappingRanges = mapRanges.filter(mapRange => {
        sourceRange._2 == mapRange.sourceRangeStart
      })

      if (overlappingRanges.isEmpty) {
        Set(sourceRange)
      } else {
        calculateDestinationRanges(sourceRange, overlappingRanges)
      }
    })
  }

  private def calculateDestinationRanges(sourceRange: (Long, Long), overlappingRanges: List[MapRange]): Set[(Long, Long)] = {
    Set((sourceRange._1, sourceRange._2 - 1), (overlappingRanges.head.destinationRangeStart, overlappingRanges.head.destinationRangeStart))
  }

  override def equals(that: Any): Boolean = {
    that match {
      case that: AlmanacMap =>
        that.isInstanceOf[AlmanacMap] &&
        that.source == source &&
        that.destination == destination &&
        that.mapRanges.equals(mapRanges)
      case _ => false
    }
  }
}
