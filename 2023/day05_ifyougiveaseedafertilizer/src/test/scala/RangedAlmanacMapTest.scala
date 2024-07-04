import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  describe("mapSourceValues") {
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
}
