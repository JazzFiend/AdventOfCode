object RangedSeedParser {
  def parseSeedsAsRange(almanacText: List[String]): List[(Long, Long)] = {
    val seedsText = extractSeedsAsStringList(almanacText)
    seedsText
      .zipWithIndex.flatMap((seedItem, index) => combineSeedPairs(seedsText, index))
      .map(seedPairs => (seedPairs._1, seedPairs._1 + seedPairs._2 - 1))
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
