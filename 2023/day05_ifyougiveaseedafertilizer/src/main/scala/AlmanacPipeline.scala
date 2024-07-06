import scala.annotation.tailrec

// Need to use polymorphism for the AlmanacMaps
object AlmanacPipeline {
  def processPipeline(seeds: List[Long], maps: List[DiscreteAlmanacMap]): List[Long] = {
    processNextMap(maps, "seed", seeds)
  }

  @tailrec
  private def processNextMap(maps: List[DiscreteAlmanacMap], source: String, sourceValues: List[Long]): List[Long] = {
    val filteredBySource = maps.filter(map => map.source == source)
    if(filteredBySource.isEmpty) {
      sourceValues
    } else {
      val mapToUse = filteredBySource.head
      val mappedValues = mapToUse.mapSourceValues(sourceValues)
      processNextMap(maps, mapToUse.destination, mappedValues)
    }
  }
}
