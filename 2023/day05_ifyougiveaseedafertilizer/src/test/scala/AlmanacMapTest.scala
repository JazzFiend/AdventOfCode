import org.scalatest.funspec.AnyFunSpec

import scala.collection.immutable.List

class AlmanacMapTest extends AnyFunSpec {
  describe("Equals") {
    it("Two identical AlmanacMaps should be equal") {
      val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      assert(one == two)
    }

    describe("Not equals") {
      it("Different source") {
        val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = AlmanacMap("different", "destination", List(MapRange(3, 2, 1)))
        assert(one != two)
      }

      it("Different destination") {
        val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = AlmanacMap("source", "different", List(MapRange(3, 2, 1)))
        assert(one != two)
      }

      it("Different mapRanges") {
        val one = AlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = AlmanacMap("source", "destination", List.empty)
        assert(one != two)
      }
    }
  }

  describe("mapSourceValues") {
    it("A map with no range should act as a pass through") {
      val almanac = AlmanacMap("a", "b", List.empty)
      val ints = (0 to 100).toList
      assert(almanac.mapSourceValues(ints) == ints)
    }

    it("A map that redefines a single value with one map") {
      val almanac = AlmanacMap("a", "b", List(MapRange(45, 69, 1)))
      val expected = (0 to 68).toList.concat(List(45).concat((70 to 100).toList))
      assert(almanac.mapSourceValues((0 to 100).toList) == expected)
    }

    it("A map that redefines a range of values with one map") {
      val almanac = AlmanacMap("a", "b", List(MapRange(10, 60, 10)))
      val expected = (0 to 59).toList.concat((10 to 19).toList).concat((70 to 100).toList)
      assert(almanac.mapSourceValues((0 to 100).toList) == expected)
    }
  }
}
