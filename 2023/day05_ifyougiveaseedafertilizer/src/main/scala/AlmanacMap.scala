class AlmanacMap(val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(numbersToMap: List[Int]):List[Int] = {
    val mappings = computeMappings
    numbersToMap.map(inputNumber => {
      val allMapRangeResults = mapAgainstAllRanges(mappings, inputNumber)
      if(isPassThrough(inputNumber, allMapRangeResults)) {
        inputNumber
      } else {
        extractDifferentNumber(inputNumber, allMapRangeResults)
      }
    })
  }

  private def computeMappings: List[List[Int]] = {
    mapRanges.map(mapRange => {
      (mapRange.sourceRangeStart until mapRange.sourceRangeStart + mapRange.rangeLength).toList
    })
  }

  private def mapAgainstAllRanges(mappings: List[List[Int]], inputNumber: Int): List[Int] = {
    mappings.zipWithIndex.map((mapping, index) => {
      if (mapping.contains(inputNumber)) {
        val offset = mapping.indexOf(inputNumber)
        mapRanges(index).destinationRangeStart + offset
      } else {
        inputNumber
      }
    })
  }

  private def isPassThrough(inputNumber: Int, allMapRangeResults: List[Int]) = {
    allMapRangeResults.forall(result => result == inputNumber)
  }

  private def extractDifferentNumber(inputNumber: Int, allMapRangeResults: List[Int]) = {
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
