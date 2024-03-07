object SeedParser {
  def parseSeeds(almanacText: List[String]): List[Long] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    extractSeedValues(extractSeedsAsStringList(almanacText))
  }

  private def extractSeedValues(splitSeeds: List[String]): List[Long] = {
    splitSeeds.map(seed => seed.toLong)
  }

  private def hasValidSeeds(almanacText: List[String]): Boolean = {
    if (almanacText.isEmpty ||
      almanacText.head.split(":").length < 2) {
      return false
    }
    true
  }

  def parseSeedsAsRange(almanacText: List[String]): List[Long] = {
    val seedsText = extractSeedsAsStringList(almanacText)
    seedsText
      .zipWithIndex.flatMap((seedItem, index) => combineSeedPairs(seedsText, index))
      .flatMap(seedPairs => (seedPairs._1 to (seedPairs._1 + seedPairs._2 - 1)).toList)
  }

  private def extractSeedsAsStringList(almanacText: List[String]): List[String] = {
    almanacText.head.split(":").toList.last.trim.split(" ").toList
  }

  private def combineSeedPairs(seedsText: List[String], index: Int) = {
    if (index % 2 == 0) {
      Some((seedsText(index).toLong, seedsText(index + 1).toLong))
    } else {
      None
    }
  }
}
