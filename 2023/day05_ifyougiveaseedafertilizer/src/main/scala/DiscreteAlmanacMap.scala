class DiscreteAlmanacMap(val source: String, val destination: String, val mapRanges: List[MapRange]) {
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

  override def equals(that: Any): Boolean = {
    that match {
      case that: DiscreteAlmanacMap =>
        that.isInstanceOf[DiscreteAlmanacMap] &&
        that.source == source &&
        that.destination == destination &&
        that.mapRanges.equals(mapRanges)
      case _ => false
    }
  }
}
