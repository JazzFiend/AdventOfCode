import scala.annotation.tailrec

object AlmanacPipeline {
  def processDiscretePipeline(seeds: List[Long], maps: List[DiscreteAlmanacMap]): List[Long] = {
    processNextMapDiscrete(maps, "seed", seeds)
  }
  
  def processRangedPipeline(seeds: List[(Long, Long)], maps: List[RangedAlmanacMap]): List[(Long, Long)] = {
    seeds
  }

  @tailrec
  private def processNextMapDiscrete(maps: List[DiscreteAlmanacMap], source: String, sourceValues: List[Long]): List[Long] = {
    val filteredBySource = maps.filter(map => map.source == source)
    if (filteredBySource.isEmpty) {
      sourceValues
    } else {
      val mapToUse = filteredBySource.head
      val mappedValues = mapToUse.mapSourceValues(sourceValues)
      processNextMapDiscrete(maps, mapToUse.destination, mappedValues)
    }
  }
}
