class RangedAlmanacMap (val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(rangesToMap: List[(Long, Long)]): List[(Long, Long)] = {
    val mapRange = mapRanges.head
    val range = rangesToMap.head
    mapRange.rangeMap(range)
  }
}
