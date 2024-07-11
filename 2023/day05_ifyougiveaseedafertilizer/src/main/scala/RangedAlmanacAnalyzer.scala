object RangedAlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    val seeds = SeedParser.parseRangedSeeds(almanac)
    val almanacMaps = AlmanacMapParser.parseMapsRanged(almanac)
    val finalRanges = AlmanacPipeline.processRangedPipeline(seeds, almanacMaps)
    finalRanges.map(range => range._1).min
  }
}
