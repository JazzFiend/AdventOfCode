class RangedAlmanacMap (val source: String, val destination: String, val mapRanges: List[MapRange]) {
  // **** REFACTOR STEP *****
  def mapSourceValues(sources: List[(Long, Long)]): List[(Long, Long)] = {
    sources.flatMap(source => {
      val overlaps = mapRanges.flatMap(mapRange => determineOverlaps(mapRange, source))
      val slicedSources = RangeDivider.divide(source, overlaps)
      mapSliced(slicedSources)
    })
  }

  private def determineOverlaps(mapRange: MapRange, source: (Long, Long)): List[(Long, Long)] = {
    val potentialOverlap = mapRange.findRangeOverlap(source)
    if (potentialOverlap.isEmpty) {
      List.empty
    } else {
      List(potentialOverlap.get)
    }
  }

  private def mapSliced(slicedSources: List[(Long, Long)]) = {
    slicedSources
      .flatMap(slicedSource => {
        val mapped = mapRanges
          .flatMap(mapRange => mapRange.mapTuple(slicedSource))
          .distinct
        filterUnmapped(slicedSource, mapped)
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
