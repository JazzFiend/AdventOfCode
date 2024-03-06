object SeedParser {
  def parseSeeds(almanacText: List[String]): List[Long] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    extractSeedValues(extractSeedsAsStringList(almanacText))
  }

  def parseSeedsAsRange(almanacText: List[String]): List[Long] = {
    val seedsText = extractSeedsAsStringList(almanacText)
    val seedRange = (seedsText.head.toLong, seedsText.last.toLong)
    (seedRange._1 to (seedRange._1 + seedRange._2 - 1)).toList
  }

  private def extractSeedsAsStringList(almanacText: List[String]): List[String] = {
    almanacText.head.split(":").toList.last.trim.split(" ").toList
  }

  private def hasValidSeeds(almanacText: List[String]): Boolean = {
    if (almanacText.isEmpty ||
      almanacText.head.split(":").length < 2) {
      return false
    }
    true
  }

  private def extractSeedValues(splitSeeds: List[String]): List[Long] = {
    splitSeeds.map(seed => seed.toLong)
  }
}
