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

  // **** We should combine the findOverlap and rangeMap functions, There's no reason to find the overlap without the range as well.
//  describe("findRangeOverlap") {
//
//
//
//
//
//
//
//    it("Overlap all numbers in the middle of the range") {
//      val mapRange = new MapRange(10, 20, 5)
//      val input = (21L, 23L)
//      assert(mapRange.findRangeOverlap(input).get == input)
//    }
//
//    it("Input range overlaps and then some") {
//      val mapRange = new MapRange(10, 20, 5)
//      val input = (1L, 100L)
//      assert(mapRange.findRangeOverlap(input).get == (20L, 24L))
//    }
//  }

  describe("rangeMap") {
    it("No overlap with numbers too small") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (0L, 5L)
      assert(mapRange.rangeMap(input) == List(input))
    }

    it("Map min number") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (0L, 10L)
      val expected = List((0L, 9L), (500L, 500L))
      assert(mapRange.rangeMap(input) == expected)
    }

    it("Map several small numbers") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (0L, 15L)
      val expected = List((0L, 9L), (500L, 505L))
      assert(mapRange.rangeMap(input) == expected)
    }

    it("Exact map") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (10L, 29L)
      val expected = List((500L, 519L))
      assert(mapRange.rangeMap(input) == expected)
    }

    it("Map several large numbers") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (25L, 40L)
      val expected = List((515L, 519L), (30L, 40L))
      assert(mapRange.rangeMap(input) == expected)
    }

    it("Map largest number") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (29L, 32L)
      val expected = List((519L, 519L), (30L, 32L))
      assert(mapRange.rangeMap(input) == expected)
    }

    it("No overlap with numbers too big") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (100L, 150L)
      assert(mapRange.rangeMap(input) == List(input))
    }

    it("Map all input numbers in the middle of a big range") {
      val mapRange = new MapRange(500L, 10L, 20L)
      val input = (15L, 20L)
      val expected = List((505L, 510L))
      assert(mapRange.rangeMap(input) == expected)
    }
  }
}
