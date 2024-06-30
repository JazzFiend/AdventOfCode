object DiscreteAlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    val seeds = SeedParser.parseSeeds(almanac)
    val maps = AlmanacMapParser.parseMaps(almanac)
    val finalValues = AlmanacPipeline.processPipeline(seeds, maps)
    finalValues.min
  }
}
