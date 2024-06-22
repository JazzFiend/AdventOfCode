import org.scalatest.funspec.AnyFunSpec

class SeedParserTest extends AnyFunSpec {
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
