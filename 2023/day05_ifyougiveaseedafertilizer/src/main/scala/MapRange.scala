class MapRange(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
  def isInRange(number: Long): Boolean = {
    number >= sourceRangeStart && number <= sourceRangeEnd
  }

  def findRangeOverlap(inputRange: (Long, Long)): Option[(Long, Long)] = {
    if(inputRange._1 > sourceRangeEnd) {
      return None
    } else if(inputRange._1 > sourceRangeStart && inputRange._2 < sourceRangeEnd) {
      return Some(inputRange)
    } else if(inputRange._2 >= sourceRangeStart) {
      return Some((sourceRangeStart, inputRange._2))
    }
    None
  }

  private def sourceRangeEnd = {
    sourceRangeStart + rangeLength - 1
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
