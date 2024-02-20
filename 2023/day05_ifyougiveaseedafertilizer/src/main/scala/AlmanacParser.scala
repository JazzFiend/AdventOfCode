object AlmanacParser {
  def parseSeeds(almanacText: List[String]): List[Int] = {
    if (almanacText.isEmpty) { return List.empty }

    val splitSeeds = almanacText.head.split(":")
    if(splitSeeds.length != 2) { return List.empty }
    List(splitSeeds.last.trim.toInt)
  }

  def parseMaps(almanacText: List[String]): List[AlmanacMap] = {
    List.empty
  }
}
