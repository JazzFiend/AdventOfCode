class RangedAlmanacMap (val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(rangesToMap: List[(Long, Long)]):List[(Long, Long)] = {
    rangesToMap
  }
}
