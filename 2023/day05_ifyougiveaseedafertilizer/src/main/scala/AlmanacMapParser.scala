object AlmanacMapParser {
  def parseMaps(almanacText: List[String]): List[AlmanacMap] = {
    if (!hasValidMaps(almanacText)) {
      return List.empty
    }

    val mapRanges = almanacText
      .slice(2, almanacText.size)
      .map(mapRange => extractMapRange(mapRange))

    val mapTitles = extractMapTitles(almanacText)
    List(AlmanacMap(mapTitles.head, mapTitles.last, mapRanges))
  }

  private def hasValidMaps(almanacText: List[String]): Boolean = {
    if (almanacText.length < 3) {
      return false
    }
    if (!almanacText(1).contains("-to-")) {
      throw new RuntimeException("Map title does not contain \"-to-\"")
    }
    // DRY this out?
    almanacText.slice(2, almanacText.size).foreach(mapRange => {
      if(mapRange.split(" ").length != 3) {
        throw new RuntimeException("Map range is not formatted correctly")
      }
    })
    true
  }

  private def extractMapRange(almanacText: String): MapRange = {
    val mapRangeTokenized = almanacText.split(" ")
    MapRange(mapRangeTokenized(0).toInt, mapRangeTokenized(1).toInt, mapRangeTokenized(2).toInt)
  }

  private def extractMapTitles(almanacText: List[String]): List[String] = {
    val mapTitles = almanacText(1).split(" ").head.split("-")
    List(mapTitles.head, mapTitles.last)
  }
}
