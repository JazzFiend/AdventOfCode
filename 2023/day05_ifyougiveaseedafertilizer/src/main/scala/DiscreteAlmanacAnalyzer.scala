object DiscreteAlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    val seeds = SeedParser.parseDiscreteSeeds(almanac)
    val maps = AlmanacMapParser.parseMapsDiscrete(almanac)
    val finalValues = AlmanacPipeline.processDiscretePipeline(seeds, maps)
    finalValues.min
  }
}
