object AlmanacParser {
  def parseSeeds(almanacText: List[String]): List[Int] = {
    if (!hasValidSeeds(almanacText)) { return List.empty }

    val splitSeeds = almanacText.head.split(":").toList
    extractSeedValues(splitSeeds)
  }

  def parseMaps(almanacText: List[String]): List[AlmanacMap] = {
    if (almanacText.length < 3) {
      return List.empty
    }

    val mapRange = extractMapRange(almanacText)
    val mapTitles = extractMapTitles(almanacText, mapRange)
    val almanacMap = AlmanacMap(mapTitles.head, mapTitles.last, List(mapRange))
    List(almanacMap)
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

  private def extractMapRange(almanacText: List[String]): MapRange = {
    val mapRangeTokenized = almanacText.last.split(" ")
    MapRange(mapRangeTokenized(0).toInt, mapRangeTokenized(1).toInt, mapRangeTokenized(2).toInt)
  }

  private def extractMapTitles(almanacText: List[String], mapRange: MapRange): List[String] = {
    val mapTitles = almanacText(1).split(" ").head.split("-")
    List(mapTitles.head, mapTitles.last)
  }
}
