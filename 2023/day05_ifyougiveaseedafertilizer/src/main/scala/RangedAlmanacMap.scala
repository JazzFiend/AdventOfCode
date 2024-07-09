class RangedAlmanacMap (val source: String, val destination: String, val mapRanges: List[MapRange]) {
  def mapSourceValues(sources: List[(Long, Long)]): List[(Long, Long)] = {
    sources.flatMap(source => {
//      val overlaps = mapRanges.map(mapRange => {
//        mapRange.findRangeOverlap(source).get
//      })
// New range divider class goes here. Once I have the range divided up by overlaps, I can map them more easily.
// I may have to modify the maptuple function, as it should return nothing if it doesn't map now,


      val results = mapRanges.flatMap(mapRanges => {
        mapRanges.mapTuple(source)
      }).distinct
      filterUnmapped(source, results)
    })
  }
  
  

  private def filterUnmapped(source: (Long, Long), results: List[(Long, Long)]) = {
    if (sourceReportedErroneously(source, results)) {
      results.filterNot(tup => tup == source)
    } else {
      results
    }
  }

  private def sourceReportedErroneously(source: (Long, Long), results: List[(Long, Long)]) = {
    results.contains(source) && results.length > 1
  }

  override def equals(that: Any): Boolean = {
    that match {
      case that: RangedAlmanacMap =>
        that.isInstanceOf[RangedAlmanacMap] &&
          that.source == source &&
          that.destination == destination &&
          that.mapRanges.equals(mapRanges)
      case _ => false
    }
  }
}
