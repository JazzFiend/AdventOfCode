class AlmanacMap(val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(valuesToMap: List[Int]):List[Int] = {
    valuesToMap
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
