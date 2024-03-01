import org.scalatest.funspec.AnyFunSpec

class AlmanacPipelineTest extends AnyFunSpec{
  it("Seeds with no maps should just be a pass through") {
    val seeds = List(34, 58, 1, 334)
    assert(AlmanacPipeline.processPipeline(seeds, List.empty) == List(34, 58, 1, 334))
  }

  it("Seeds and one map") {
    val seeds = List(1, 2, 3, 4)
    val maps = List(AlmanacMap("a", "b", List(MapRange(100, 2, 2))))
    assert(AlmanacPipeline.processPipeline(seeds, maps) == List(1, 100, 101, 4))
  }
}
