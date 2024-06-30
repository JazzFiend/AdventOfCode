class MapRange(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
  def isInRange(number: Long): Boolean = {
    number >= sourceRangeStart && number <= sourceRangeStart + rangeLength - 1
  }

  def findRangeOverlap(inputRange: (Long, Long)): Option[(Long, Long)] = {
    if(inputRange._2 == sourceRangeStart) {
      return Some((sourceRangeStart, sourceRangeStart))
    }
    None
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
