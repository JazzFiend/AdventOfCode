class RangedAlmanacMap (val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(sources: List[(Long, Long)]): List[(Long, Long)] = {
    val mapRange = mapRanges.head
    sources.flatMap(source => mapRange.mapTuple(source))
  }
}
