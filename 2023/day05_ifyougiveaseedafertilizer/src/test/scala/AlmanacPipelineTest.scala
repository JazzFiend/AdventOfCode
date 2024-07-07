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
      val seeds = List((10L, 10L), (100L, 50L))
      assert(AlmanacPipeline.processRangedPipeline(seeds, List.empty) == seeds)
    }
  }
}
