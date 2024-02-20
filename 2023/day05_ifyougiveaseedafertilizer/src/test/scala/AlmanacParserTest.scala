import org.scalatest.funspec.AnyFunSpec

class AlmanacParserTest extends AnyFunSpec{
  describe("parseSeeds") {
    describe("Error cases") {
      it("An empty almanac should give an empty list of seeds") {
        assert(AlmanacParser.parseSeeds(List.empty) == List.empty)
      }

      it("An almanac with no seeds listed should give an empty list of seeds") {
        val noSeeds = List("seeds:")
        assert(AlmanacParser.parseSeeds(noSeeds) == List.empty)
      }
    }

    it("One seed") {
      val oneSeed = List("seeds: 68")
      assert(AlmanacParser.parseSeeds(oneSeed) == List(68))
    }

    it("Many seeds") {
      val manySeeds = List("seeds: 16 1 384 34")
      assert(AlmanacParser.parseSeeds(manySeeds) == List(16, 1, 384, 34))
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
