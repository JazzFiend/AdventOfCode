package MapRange

class MapRange(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
  private def sourceRangeEnd = {
    sourceRangeStart + rangeLength - 1
  }

  private def destinationRangeEnd = {
    destinationRangeStart + rangeLength - 1
  }

  def isInRange(number: Long): Boolean = {
    number >= sourceRangeStart && number <= sourceRangeEnd
  }

  def mapTuple(range: (Long, Long)): List[(Long, Long)] = {
    val overlap = findRangeOverlap(range)
    if (overlap.isEmpty) {
      return List(range)
    }

    computeMap(range, overlap.get)
  }

  // This should be private now.
  def findRangeOverlap(inputRange: (Long, Long)): Option[(Long, Long)] = {
    if (noOverlap(inputRange)) { return None }

    val low = Math.max(inputRange._1, sourceRangeStart)
    val high = Math.min(sourceRangeEnd, inputRange._2)
    Some(low, high)
  }

  private def noOverlap(inputRange: (Long, Long)) = {
    inputRange._2 < sourceRangeStart || inputRange._1 > sourceRangeEnd
  }

  private def computeMap(range: (Long, Long), overlap: (Long, Long)): List[(Long, Long)] = {
    if(range == overlap) {
      val startOffset = range._1 - sourceRangeStart
      val endOffset = sourceRangeEnd - range._2
      List((destinationRangeStart + startOffset, destinationRangeEnd - endOffset))
    } else if(overSizedRange(range, overlap)) {
      val noChangeStart = (range._1, overlap._1 - 1)
      val change = (destinationRangeStart, destinationRangeEnd)
      val noChangeEnd = (overlap._2 + 1, range._2)
      List(noChangeStart, change, noChangeEnd)
    } else if(overlapLeft(range, overlap)) {
      val noChange = (range._1, overlap._1 - 1)
      val change = (destinationRangeStart, destinationRangeStart + computeRangeLength(overlap))
      List(noChange, change)
    } else {
      val changeLength = rangeLength - computeRangeLength(overlap) - 1
      val change = (destinationRangeStart + changeLength, destinationRangeEnd)
      val noChange = (overlap._2 + 1, range._2)
      List(change, noChange)
    }
  }

  private def overSizedRange(range: (Long, Long), overlap: (Long, Long)) = {
    range._1 < overlap._1 && range._2 > overlap._2
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
