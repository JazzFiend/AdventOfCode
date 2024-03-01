object AlmanacPipeline {
  def processPipeline(seeds: List[Int], maps: List[AlmanacMap]): List[Int] = {
    if(maps.isEmpty) { return seeds }

    maps.head.mapSourceValues(seeds)
  }
}
