import org.scalatest.funspec.AnyFunSpec

class SeedParserTest extends AnyFunSpec {
  describe("parseSeeds") {
    describe("Error cases") {
      it("An empty almanac should give an empty list of seeds") {
        assert(SeedParser.parseSeeds(List.empty) == List.empty)
      }

      it("An almanac with no seeds listed should give an empty list of seeds") {
        val noSeeds = List("seeds:")
        assert(SeedParser.parseSeeds(noSeeds) == List.empty)
      }
    }

    it("One seed") {
      val oneSeed = List("seeds: 68")
      assert(SeedParser.parseSeeds(oneSeed) == List(68))
    }

    it("Many seeds") {
      val manySeeds = List("seeds: 16 1 384 34")
      assert(SeedParser.parseSeeds(manySeeds) == List(16, 1, 384, 34))
    }
  }

  describe("parseSeedsAsRange") {
    it("One pair") {
      val onePair = List("seeds: 23 5")
      val expected = (23 to 27).toList
      assert(SeedParser.parseSeedsAsRange(onePair) == expected)
    }

    it("Many pairs") {
      val manyPairs = List("seeds: 0 5 10 2 100 10")
      val expected = (0 to 4).concat((10 to 11)).concat(100 to 109).toList
      assert(SeedParser.parseSeedsAsRange(manyPairs) == expected)
    }
  }
}
