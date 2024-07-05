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

    it("Multiple sources - Both don't map") {
      val almanacMap = RangedAlmanacMap("source", "destination", List(new MapRange(100, 10, 10)))
      val sourceRanges = List((0L, 5L), (50L, 80L))
      assert(almanacMap.mapSourceValues(sourceRanges) == sourceRanges)
    }
  }
}
