import org.scalatest.funspec.AnyFunSpec

class SeedParserTest extends AnyFunSpec {
  // Add ability to parse ranges now.
  describe("parseDiscreteSeeds") {
    describe("Error cases") {
      it("An empty almanac should give an empty list of seeds") {
        assert(SeedParser.parseDiscreteSeeds(List.empty) == List.empty)
      }

      it("An almanac with no seeds listed should give an empty list of seeds") {
        val noSeeds = List("seeds:")
        assert(SeedParser.parseDiscreteSeeds(noSeeds) == List.empty)
      }
    }

    it("One seed") {
      val oneSeed = List("seeds: 68")
      assert(SeedParser.parseDiscreteSeeds(oneSeed) == List(68))
    }

    it("Many seeds") {
      val manySeeds = List("seeds: 16 1 384 34")
      assert(SeedParser.parseDiscreteSeeds(manySeeds) == List(16, 1, 384, 34))
    }
  }

  describe("Parse Ranged Seeds") {
    describe("Error cases") {
      it("An empty almanac should give an empty list of seeds") {
        assert(SeedParser.parseRangedSeeds(List.empty) == List.empty)
      }

      it("An almanac with no seeds listed should give an empty list of seeds") {
        val noSeeds = List("seeds:")
        assert(SeedParser.parseRangedSeeds(noSeeds) == List.empty)
      }
    }

    it("One seed range") {
      val oneSeed = List("seeds: 17 42")
      assert(SeedParser.parseRangedSeeds(oneSeed) == List((17L, 58L)))
    }

    it("Many seed ranges") {
      val manySeeds = List("seeds: 2 5 39 55")
      assert(SeedParser.parseRangedSeeds(manySeeds) == List((2L, 6L), (39L, 93L)))
    }
  }
}
