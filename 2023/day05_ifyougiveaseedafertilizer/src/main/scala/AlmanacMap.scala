class AlmanacMap(val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(numbersToMap: List[Long]):List[Long] = {
    val mappings = computeMappings
    numbersToMap.map(inputNumber => {
      val allMapRangeResults = mapAgainstAllRanges(mappings, inputNumber)
      calculateMapResult(inputNumber, allMapRangeResults)
    })
  }

  private def computeMappings: List[List[Long]] = {
    mapRanges.map(mapRange => {
      (mapRange.sourceRangeStart until mapRange.sourceRangeStart + mapRange.rangeLength).toList
    })
  }

  private def mapAgainstAllRanges(mappings: List[List[Long]], inputNumber: Long): List[Long] = {
    mappings.zipWithIndex.map((mapping, index) => {
      if (mapping.contains(inputNumber)) {
        val offset = mapping.indexOf(inputNumber)
        mapRanges(index).destinationRangeStart + offset
      } else {
        inputNumber
      }
    })
  }

  private def calculateMapResult(inputNumber: Long, allMapRangeResults: List[Long]) = {
    allMapRangeResults.fold(inputNumber)((prev, next) => {
      if (next != inputNumber) {
        next
      } else {
        prev
      }
    })
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
