import org.scalatest.funspec.AnyFunSpec

class AlmanacParserTest extends AnyFunSpec{
  describe("parseSeeds") {
    it("An empty almanac should give an empty list of seeds") {
      assert(AlmanacParser.parseSeeds(List.empty) == List.empty)
    }

    it("An almanac with no seeds listed should give an empty list of seeds") {
      val noSeeds = List("seeds:")
      assert(AlmanacParser.parseSeeds(noSeeds) == List.empty)
    }
  }

  describe("parseMaps") {
    it("An empty almanac should give an empty list of entries") {
      assert(AlmanacParser.parseMaps(List.empty) == List.empty)
    }

    it("An almanac with just seeds should result in an empty list") {
      val justSeeds = List("seeds: 1 4 6 7")
      assert(AlmanacParser.parseMaps(justSeeds) == List.empty)
    }
  }
}
