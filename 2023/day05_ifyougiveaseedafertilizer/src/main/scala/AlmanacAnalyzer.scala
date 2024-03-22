object AlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    calculateLowestLocation(almanac, SeedParser.parseSeeds(almanac))
  }

  def findLowestLocationBySeedRange(almanac: List[String]): Long = {
    val seedRanges = RangedSeedParser.parseSeedsAsRange(almanac);
    calculateLowestLocation(almanac, SeedParser.parseSeedsAsRange(almanac))
  }

  private def calculateLowestLocation(almanac: List[String], seeds: List[Long]) = {
    val maps = AlmanacMapParser.parseMaps(almanac)
    val finalValues = AlmanacPipeline.processPipeline(seeds, maps)
    finalValues.min
  }
}

// The solution works, but its blowing the heap space on the puzzle again. Need to figure out how to speed it up.
// Yeah, the seed ranges are massive. Instead of making them lists, they should be a range of numbers. I then need to get the other classes to support ranges.