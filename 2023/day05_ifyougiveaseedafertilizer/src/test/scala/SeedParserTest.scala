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
//
//      it("An almanac with no seeds listed should give an empty list of seeds") {
//        val noSeeds = List("seeds:")
//        assert(SeedParser.parseDiscreteSeeds(noSeeds) == List.empty)
//      }
    }
//
//    it("One seed") {
//      val oneSeed = List("seeds: 68")
//      assert(SeedParser.parseDiscreteSeeds(oneSeed) == List(68))
//    }
//
//    it("Many seeds") {
//      val manySeeds = List("seeds: 16 1 384 34")
//      assert(SeedParser.parseDiscreteSeeds(manySeeds) == List(16, 1, 384, 34))
//    }
  }
}
