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
    it("No overlap with numbers too small") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (5L, 10L)
      assert(mapRange.findRangeOverlap(input).isEmpty)
    }

    it("Overlap smallest number") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (10L, 20L)
      assert(mapRange.findRangeOverlap(input).get == (20L, 20L))
    }

    it("Overlap several small numbers") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (10L, 23L)
      assert(mapRange.findRangeOverlap(input).get == (20L, 23L))
    }

    it("Overlap all numbers") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (20L, 24L)
      assert(mapRange.findRangeOverlap(input).get == (20L, 24L))
    }

    it("Overlap several large numbers") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (22L, 38L)
      assert(mapRange.findRangeOverlap(input).get == (22L, 24L))
    }

    it("Overlap largest number") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (24L, 57L)
      assert(mapRange.findRangeOverlap(input).get == (24L, 24L))
    }

    it("No overlap with numbers too large") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (40L, 60L)
      assert(mapRange.findRangeOverlap(input).isEmpty)
    }

    it("Overlap all numbers in the middle of the range") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (21L, 23L)
      assert(mapRange.findRangeOverlap(input).get == (21L, 23L))
    }

    it("Input range overlaps and then some") {
      val mapRange = new MapRange(10, 20, 5)
      val input = (1L, 100L)
      assert(mapRange.findRangeOverlap(input).get == (20L, 24L))
    }
  }

  describe("rangeMap") {
    it("No overlap with numbers too small") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (0L, 5L)
      assert(mapRange.rangeMap(input) == List(input))
    }

    it("Overlap min number") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (0L, 10L)
      val expected = List((0L, 9L), (500L, 500L))
      assert(mapRange.rangeMap(input) == expected)
    }

    it("Overlap several small numbers") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (0L, 15L)
      val expected = List((0L, 9L), (500L, 505L))
      assert(mapRange.rangeMap(input) == expected)
    }
  }
}
