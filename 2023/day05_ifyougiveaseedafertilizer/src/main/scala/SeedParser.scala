object SeedParser {
  def parseSeeds(almanacText: List[String]): List[Int] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = almanacText.head.split(":").toList
    extractSeedValues(splitSeeds)
  }

  private def hasValidSeeds(almanacText: List[String]): Boolean = {
    if (almanacText.isEmpty ||
      almanacText.head.split(":").length < 2) {
      return false
    }
    true
  }

  private def extractSeedValues(splitSeeds: List[String]): List[Int] = {
    splitSeeds.last.trim
      .split(" ")
      .map(seed => seed.toInt)
      .toList
  }
}
