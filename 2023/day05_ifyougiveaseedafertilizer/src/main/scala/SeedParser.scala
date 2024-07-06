object SeedParser {
  def parseDiscreteSeeds(almanacText: List[String]): List[Long] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = splitTextAndSeedValues(almanacText)
    extractSeedValues(splitSeeds)
  }

  def parseRangedSeeds(almanacText: List[String]): List[(Long, Long)] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = splitTextAndSeedValues(almanacText)
    val seedValues = splitSeeds.last.trim.split(" ")
    val endValue = seedValues(0).toLong + seedValues(1).toLong - 1L
    List((seedValues(0).toLong, endValue))
  }

  private def splitTextAndSeedValues(almanacText: List[String]) = {
    almanacText.head.split(":").toList
  }

  private def hasValidSeeds(almanacText: List[String]): Boolean = {
    if (almanacText.isEmpty ||
      almanacText.head.split(":").length < 2) {
      return false
    }
    true
  }

  private def extractSeedValues(splitSeeds: List[String]): List[Long] = {
    splitSeeds.last.trim
      .split(" ")
      .map(seed => seed.toLong)
      .toList
  }
}
