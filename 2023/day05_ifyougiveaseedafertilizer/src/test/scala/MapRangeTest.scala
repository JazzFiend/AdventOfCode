import org.scalatest.funspec.AnyFunSpec

class MapRangeTest extends AnyFunSpec {
  describe("Equals") {
    it("Two identical MapRanges should be equal") {
      val one = MapRange(1, 2, 3)
      val two = MapRange(1, 2, 3)
      assert(one == two)
    }
  }

  describe("Not equals") {
    it("Different destinationRangeStart") {
      val one = MapRange(10, 2, 3)
      val two = MapRange(1, 2, 3)
      assert(one != two)
    }

    it("Different sourceRangeStart") {
      val one = MapRange(1, 2, 3)
      val two = MapRange(1, 3, 3)
      assert(one != two)
    }

    it("Different rangeLength") {
      val one = MapRange(1, 2, 3)
      val two = MapRange(1, 2, 9)
      assert(one != two)
    }
  }

  describe("findRangeOverlap") {
    it("No range overlap should return nothing") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (40L, 60L)
      assert(mapRange.findRangeOverlap(input).isEmpty)
    }

    it("Overlap one number on the right") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (10L, 20L)
      assert(mapRange.findRangeOverlap(input).get == (20L, 20L))
    }
  }
}
