import org.scalatest.funspec.AnyFunSpec

class AlmanacPipelineTest extends AnyFunSpec{
  describe("Discrete Version") {
    it("Seeds with no maps should just be a pass through") {
      val seeds = List(34L, 58L, 1L, 334L)
      assert(AlmanacPipeline.processDiscretePipeline(seeds, List.empty) == List(34, 58, 1, 334))
    }

    it("Seeds and one map") {
      val seeds = List(1L, 2L, 3L, 4L)
      val maps = List(DiscreteAlmanacMap("seed", "b", List(MapRange(100, 2, 2))))
      assert(AlmanacPipeline.processDiscretePipeline(seeds, maps) == List(1, 100, 101, 4))
    }

    it("Several maps") {
      val seeds = List(1L, 2L, 3L, 4L)
      val maps = List(
        DiscreteAlmanacMap("seed", "mid", List(MapRange(20, 3, 2))),
        DiscreteAlmanacMap("mid", "end", List(MapRange(20, 1, 2))),
      )
      assert(AlmanacPipeline.processDiscretePipeline(seeds, maps) == List(20, 21, 20, 21))
    }
  }

  describe("Ranged Version") {
    it("Seeds with no maps should just be a pass through") {
      val seeds = List((10L, 15L), (50L, 100L))
      assert(AlmanacPipeline.processRangedPipeline(seeds, List.empty) == seeds)
    }

    it("Seeds and one map") {
      val seeds = List((10L, 15L), (50L, 100L))
      val maps = List(RangedAlmanacMap("seed", "b", List(MapRange(0, 10, 2))))
      assert(AlmanacPipeline.processRangedPipeline(seeds, maps) == List((0L, 1L), (12L, 15L), (50L, 100)))
    }

    it("Several maps") {
      val seeds = List((10L, 15L), (50L, 100L))
      val maps = List(
        RangedAlmanacMap("seed", "mid", List(MapRange(0, 10, 2))),
        RangedAlmanacMap("mid", "end", List(MapRange(1000, 70, 10))),
      )
      assert(AlmanacPipeline.processRangedPipeline(seeds, maps) == List((0L, 1L), (12L, 15L), (50L, 69L), (1000L, 1009L), (80L, 100L)))
    }
  }
}
