object SeedParser {
  def parseDiscreteSeeds(almanacText: List[String]): List[Long] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = splitTextAndSeedValues(almanacText)
    extractSeedValues(splitSeeds)
  }

  def parseRangedSeeds(almanacText: List[String]): List[(Long, Long)] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = splitTextAndSeedValues(almanacText)
    val seedValues = seedsAsList(splitSeeds)
    parseAsRanges(seedValues)
  }

  private def hasValidSeeds(almanacText: List[String]): Boolean = {
    if (almanacText.isEmpty ||
      almanacText.head.split(":").length < 2) {
      return false
    }
    true
  }

  private def splitTextAndSeedValues(almanacText: List[String]) = {
    almanacText.head.split(":").toList
  }

  private def extractSeedValues(splitSeeds: List[String]): List[Long] = {
    seedsAsList(splitSeeds)
      .map(seed => seed.toLong)
      .toList
  }

  private def seedsAsList(splitSeeds: List[String]) = {
    splitSeeds.last.trim.split(" ")
  }

  private def parseAsRanges(seedValues: Array[String]) = {
    seedValues.grouped(2).toList.map(startRangePair => {
      val endValue = startRangePair.head.toLong + startRangePair.last.toLong - 1L
      (startRangePair.head.toLong, endValue)
    })
  }
}
