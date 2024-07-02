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
