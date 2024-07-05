import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  describe("mapSourceValues") {
    describe("Single MapRange, single source range") {
      it("No source values to map") {
        val almanacMap = RangedAlmanacMap("source", "destination", List(new MapRange(100, 10, 10)))
        val sourceRanges = List((50L, 60L))
        assert(almanacMap.mapSourceValues(sourceRanges) == sourceRanges)
      }

      it("Map all values given") {
        val almanacMap = RangedAlmanacMap("source", "destination", List(new MapRange(100, 10, 10)))
        val sourceRanges = List((10L, 19L))
        assert(almanacMap.mapSourceValues(sourceRanges) == List((100L, 109L)))
      }
    }

    it("Multiple sources") {
      val almanacMap = RangedAlmanacMap("source", "destination", List(new MapRange(100, 10, 10)))
      val sourceRanges = List((0L, 30L), (50L, 80L))
      val expected = List((0L, 9L), (100L, 109L), (20L, 30L), (50L, 80L))
      assert(almanacMap.mapSourceValues(sourceRanges) == expected)
    }

    it("Several map ranges") {
      val mapRanges = List(new MapRange(100, 10, 10), new MapRange(200, 50, 11))
      val almanacMap = RangedAlmanacMap("source", "destination", mapRanges)
      val sourceRanges = List((0L, 30L), (50L, 80L))
      val expected = List((0L, 9L), (100L, 109L), (20L, 30L), (200L, 210L), (61L, 80L))
      assert(almanacMap.mapSourceValues(sourceRanges) == expected)
    }
  }
}
