class AlmanacMap(val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(valuesToMap: List[Int]):List[Int] = {
    if(mapRanges.isEmpty) { return valuesToMap }

    val modified = extractModified
    valuesToMap.map(value => {
      if(modified.contains(value)) {
        val offset = modified.indexOf(value)
        mapRanges.head.destinationRangeStart + offset
      } else {
        value
      }
    })
  }

  private def extractModified = {
    (mapRanges.head.sourceRangeStart until mapRanges.head.sourceRangeStart + mapRanges.head.rangeLength).toList
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
