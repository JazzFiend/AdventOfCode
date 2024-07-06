object SeedParser {
  def parseDiscreteSeeds(almanacText: List[String]): List[Long] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = almanacText.head.split(":").toList
    extractSeedValues(splitSeeds)
  }

  def parseRangedSeeds(almanacText: List[String]): List[(Long, Long)] = {
    List.empty
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
