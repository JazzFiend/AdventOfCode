import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  describe("mapSourceValues") {
    it("No Overlap") {
      val almanacMap = new RangedAlmanacMap("source", "destination", List(new MapRange(10, 20, 5)))
      val sourceRanges = List((40L, 50L))
      assert(almanacMap.mapSourceValues(sourceRanges) == sourceRanges)
    }
  }
}
