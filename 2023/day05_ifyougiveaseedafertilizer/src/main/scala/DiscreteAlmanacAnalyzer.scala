object DiscreteAlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    val seeds = SeedParser.parseDiscreteSeeds(almanac)
    val maps = new AlmanacMapParser().parseMapsDiscrete(almanac)
    val finalValues = AlmanacPipeline.processPipeline(seeds, maps)
    finalValues.min
  }
}
