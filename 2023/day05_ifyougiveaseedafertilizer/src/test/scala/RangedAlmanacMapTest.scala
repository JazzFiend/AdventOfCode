import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  describe("mapSourceValues") {
    it("No Overlap") {
      val almanacMap = new RangedAlmanacMap("source", "destination", List(new MapRange(10, 20, 5)))
      val sourceRanges = List((40L, 50L))
      assert(almanacMap.mapSourceValues(sourceRanges) == sourceRanges)
    }

// Need to modify MapRange class first. It needs to take in a range of numbers and output the numbers that are in range.

//    it("Overlap one number at beginning") {
//      val almanacMap = new RangedAlmanacMap("source", "destination", List(new MapRange(10, 20, 5)))
//      val sourceRanges = List((15L, 20L))
//      val expected = List((15L, 19L), (20L, 20L))
//      assert(almanacMap.mapSourceValues(sourceRanges) == expected)
//    }
  }
}
