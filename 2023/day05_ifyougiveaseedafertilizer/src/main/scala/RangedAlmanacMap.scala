class RangedAlmanacMap (val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(rangesToMap: List[(Long, Long)]): List[(Long, Long)] = {
    val mapRange = mapRanges.head
    val range = rangesToMap.head
    val overlapOption = mapRange.findRangeOverlap(range)
    if (overlapOption.isEmpty) { return rangesToMap }

    performMap(mapRange, range, overlapOption.get)
  }

  private def performMap(mapRange: MapRange, range: (Long, Long), overlap: (Long, Long)) = {
    val noChange = (range._1, overlap._1 - 1)
    val changed = (mapRange.destinationRangeStart, mapRange.destinationRangeStart)
    List(noChange, changed)
  }
}
