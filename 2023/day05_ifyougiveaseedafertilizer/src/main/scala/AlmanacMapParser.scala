object AlmanacMapParser {
  def parseMaps(almanacText: List[String]): List[AlmanacMap] = {
    if (!hasValidMaps(almanacText)) {
      return List.empty
    }

    val mapRange = extractMapRange(almanacText)
    val mapTitles = extractMapTitles(almanacText, mapRange)
    val almanacMap = AlmanacMap(mapTitles.head, mapTitles.last, List(mapRange))
    List(almanacMap)
  }

  private def hasValidMaps(almanacText: List[String]): Boolean = {
    if (almanacText.length < 3) {
      return false
    }
    if (!almanacText(1).contains("-to-")) {
      throw new RuntimeException("Map title does not contain \"-to-\"")
    }
    true
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
