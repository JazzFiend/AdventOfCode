import scala.annotation.tailrec

object AlmanacPipeline {
  def processPipeline(seeds: List[Long], maps: List[AlmanacMap]): List[Long] = {
    processNextMap(maps, "seed", seeds)
  }

  @tailrec
  private def processNextMap(maps: List[AlmanacMap], source: String, sourceValues: List[Long]): List[Long] = {
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
