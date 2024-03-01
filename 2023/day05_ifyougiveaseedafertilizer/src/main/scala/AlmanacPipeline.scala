import scala.annotation.tailrec

object AlmanacPipeline {
  def processPipeline(seeds: List[Int], maps: List[AlmanacMap]): List[Int] = {
    processNextMap(maps, "seed", seeds)
  }

  @tailrec
  private def processNextMap(maps: List[AlmanacMap], source: String, sourceValues: List[Int]): List[Int] = {
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
