import org.scalatest.funspec.AnyFunSpec

class RangedAlmanacMapTest extends AnyFunSpec {
  // I think this is all of the test cases that I need... I'm not consolidating ranges if there are
  // duplicates though. But I'm not sure if I need to. We're ultimately just looking for the minimum
  // value, so repeating mappings shouldn't matter. This will only come into play if all of the
  // repeats make the time complexity explode. I think I'll cross that bridge if and when I come to it.
  describe("Equals") {
    it("Two identical RangedAlmanacMaps should be equal") {
      val one = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      assert(one == two)
    }

    describe("Not equals") {
      it("Different source") {
        val one = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = RangedAlmanacMap("different", "destination", List(MapRange(3, 2, 1)))
        assert(one != two)
      }

      it("Different destination") {
        val one = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = RangedAlmanacMap("source", "different", List(MapRange(3, 2, 1)))
        assert(one != two)
      }

      it("Different mapRanges") {
        val one = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = RangedAlmanacMap("source", "destination", List.empty)
        assert(one != two)
      }

      it("Different type") {
        val one = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        assert(one != two)
      }
    }
  }
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

    it("Duplicate mapped values") {
      val mapRanges = List(new MapRange(0, 15, 37), new MapRange(37, 52, 2), new MapRange(39, 0, 15))
      val almanacMap = RangedAlmanacMap("soil", "fertilizer", mapRanges)
      val sourceRanges = List((81L, 94L), (57L, 69L))
      assert(almanacMap.mapSourceValues(sourceRanges) == sourceRanges)
    }
  }
}
