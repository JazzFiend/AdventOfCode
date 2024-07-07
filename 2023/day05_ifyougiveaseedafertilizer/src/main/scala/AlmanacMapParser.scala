import AlmanacMapParser.{createDiscreteAlmanacMap, createRangedAlmanacMap, extractMapEntries, throwIfNotValidMapEntry}

// I'm taking shortcuts adding support for Ranged maps. If things get weird, split this into a derived class.
// Things got weird. Time to break it apart.
class AlmanacMapParser {
  def parseMapsDiscrete(almanacText: List[String]): List[DiscreteAlmanacMap] = {
    extractMapEntries(almanacText)
      .map(mapEntry => {
        throwIfNotValidMapEntry(mapEntry)
        createDiscreteAlmanacMap(mapEntry)
      })
  }

  def parseMapsRanged(almanacText: List[String]): List[RangedAlmanacMap] = {
    extractMapEntries(almanacText)
      .map(mapEntry => {
        throwIfNotValidMapEntry(mapEntry)
        createRangedAlmanacMap(mapEntry)
      })
  }
}

object AlmanacMapParser {
  def parseMapsRanged(almanacText: List[String]): List[RangedAlmanacMap] = {
    extractMapEntries(almanacText)
      .map(mapEntry => {
        throwIfNotValidMapEntry(mapEntry)
        createRangedAlmanacMap(mapEntry)
      })
  }

  private def extractMapEntries(almanacText: List[String]): List[List[String]] = {
    val mapTitleLocations = almanacText
      .zipWithIndex
      .filter((text, index) => text.contains("-to-"))
      .map((text, index) => index)
    if(mapTitleLocations.isEmpty) {
      throw new RuntimeException("No valid map titles found")
    }
    sliceIntoMapEntries(mapTitleLocations, almanacText)
  }

  private def sliceIntoMapEntries(mapTitleLocations: List[Int], almanacText: List[String]): List[List[String]] = {
    val currentNextLocationsPaired = mapTitleLocations
      .zipWithIndex
      .map((titleLocation, index) => {
        if (index < mapTitleLocations.length - 1) {
          (titleLocation, mapTitleLocations(index + 1))
        } else {
          (titleLocation, -1)
        }
      })

    currentNextLocationsPaired.map((current, next) => {
      if (next > -1) {
        almanacText.slice(current, next)
      } else {
        almanacText.slice(current, almanacText.size)
      }
    })
  }

  private def createDiscreteAlmanacMap(mapEntry: List[String]): DiscreteAlmanacMap = {
    val mapRanges = removeTitle(mapEntry)
      .map(mapRange => extractMapRange(mapRange))
    val mapTitles = extractMapTitle(mapEntry)
    DiscreteAlmanacMap(mapTitles.head, mapTitles.last, mapRanges)
  }

  private def createRangedAlmanacMap(mapEntry: List[String]): RangedAlmanacMap = {
    val mapRanges = removeTitle(mapEntry)
      .map(mapRange => extractMapRange(mapRange))
    val mapTitles = extractMapTitle(mapEntry)
    RangedAlmanacMap(mapTitles.head, mapTitles.last, mapRanges)
  }

  private def throwIfNotValidMapEntry(mapEntry: List[String]): Unit = {
    removeTitle(mapEntry).foreach(mapRange => {
      if (mapRange.split(" ").length != 3) {
        throw new RuntimeException("Map range is not formatted correctly")
      }
    })
  }

  private def removeTitle(mapEntry: List[String]): List[String] = {
    mapEntry.slice(1, mapEntry.size)
  }

  private def extractMapRange(mapEntry: String): MapRange = {
    val mapRangeTokenized = mapEntry.split(" ")
    MapRange(mapRangeTokenized(0).toLong, mapRangeTokenized(1).toLong, mapRangeTokenized(2).toLong)
  }

  private def extractMapTitle(mapEntry: List[String]): List[String] = {
    val mapTitles = mapEntry.head.split(" ").head.split("-")
    List(mapTitles.head, mapTitles.last)
  }
}
