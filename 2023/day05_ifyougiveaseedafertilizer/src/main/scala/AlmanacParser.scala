object AlmanacParser {
  def parseSeeds(almanacText: List[String]): List[Int] = {
    if (almanacText.isEmpty) { return List.empty }

    val splitSeeds = almanacText.head.split(":").toList
    if(splitSeeds.length != 2) { return List.empty }

    extractSeedValues(splitSeeds)
  }

  private def extractSeedValues(splitSeeds: List[String]): List[Int] = {
    splitSeeds.last.trim
      .split(" ")
      .map(seed => seed.toInt)
      .toList
  }

  def parseMaps(almanacText: List[String]): List[AlmanacMap] = {
    List.empty
  }
}
