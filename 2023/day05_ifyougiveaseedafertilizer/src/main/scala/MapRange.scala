class MapRange(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
  private def sourceRangeEnd = {
    sourceRangeStart + rangeLength - 1
  }

  def isInRange(number: Long): Boolean = {
    number >= sourceRangeStart && number <= sourceRangeEnd
  }

  def findRangeOverlap(inputRange: (Long, Long)): Option[(Long, Long)] = {
    if (noOverlap(inputRange)) { return None }

    val low = Math.max(inputRange._1, sourceRangeStart)
    val high = Math.min(sourceRangeEnd, inputRange._2)
    Some(low, high)
  }

  private def noOverlap(inputRange: (Long, Long)) = {
    inputRange._2 < sourceRangeStart || inputRange._1 > sourceRangeEnd
  }

  def rangeMap(range: (Long, Long)): List[(Long, Long)] = {
    val overlap = findRangeOverlap(range)
    if (overlap.isEmpty) { return List(range) }

    computeMap(range, overlap.get)
  }

  private def computeMap(range: (Long, Long), overlap: (Long, Long)): List[(Long, Long)] = {
    if(range == overlap) {
      List((destinationRangeStart, destinationRangeStart + rangeLength - 1))
    } else if(overlapLeft(range, overlap)) {
      val noChange = (range._1, overlap._1 - 1)
      val change = (destinationRangeStart, destinationRangeStart + computeRangeLength(overlap))
      List(noChange, change)
    } else {
      val changeLength = rangeLength - computeRangeLength(overlap) - 1
      val change = (destinationRangeStart + changeLength, destinationRangeStart + rangeLength - 1)
      val noChange = (overlap._2 + 1, range._2)
      List(change, noChange)
    }
  }

  private def overlapLeft(range: (Long, Long), overlap: (Long, Long)): Boolean = {
    range._2 >= overlap._1 && range._1 < overlap._1
  }

  private def computeRangeLength(range: (Long, Long)): Integer = {
    (range._2 - range._1).toInt
  }

  override def equals(that: Any): Boolean = {
    that match {
      case that: MapRange =>
        that.isInstanceOf[MapRange] &&
          that.destinationRangeStart == destinationRangeStart &&
          that.sourceRangeStart == sourceRangeStart &&
          that.rangeLength == rangeLength
      case _ => false
    }
  }
}
