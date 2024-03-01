import org.scalatest.funspec.AnyFunSpec

class AlmanacPipelineTest extends AnyFunSpec{
  it("Seeds with no maps should just be a pass through") {
    val almanac = List(34, 58, 1, 334)
    assert(AlmanacPipeline.processPipeline(almanac) == List(34, 58, 1, 334))
  }
}
