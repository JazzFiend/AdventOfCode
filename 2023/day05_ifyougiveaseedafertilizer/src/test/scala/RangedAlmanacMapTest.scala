import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  describe("mapSourceValues") {
    it("No Overlap - too small") {
      val almanacMap = new RangedAlmanacMap("source", "destination", List(new MapRange(200, 100, 10)))
      val sourceRanges = List((0L, 50L))
      assert(almanacMap.mapSourceValues(sourceRanges) == sourceRanges)
    }

    // **** Should we return a List or a Set? ****
    it("Overlap one number at beginning") {
      val almanacMap = new RangedAlmanacMap("source", "destination", List(new MapRange(200, 100, 10)))
      val sourceRanges = List((90L, 100L))
      val expected = List((90L, 99L), (200L, 200L))
      assert(almanacMap.mapSourceValues(sourceRanges) == expected)
    }
  }
}
