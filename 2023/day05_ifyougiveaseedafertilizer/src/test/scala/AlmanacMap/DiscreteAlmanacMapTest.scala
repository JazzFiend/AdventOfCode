package AlmanacMap

import AlmanacMap.{DiscreteAlmanacMap, RangedAlmanacMap}
import MapRange.MapRange
import org.scalatest.funspec.AnyFunSpec

import scala.collection.immutable.List

class DiscreteAlmanacMapTest extends AnyFunSpec {
  describe("Equals") {
    it("Two identical DiscreteAlmanacMaps should be equal") {
      val one = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      val two = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
      assert(one == two)
    }

    describe("Not equals") {
      it("Different source") {
        val one = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = DiscreteAlmanacMap("different", "destination", List(MapRange(3, 2, 1)))
        assert(one != two)
      }

      it("Different destination") {
        val one = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = DiscreteAlmanacMap("source", "different", List(MapRange(3, 2, 1)))
        assert(one != two)
      }

      it("Different mapRanges") {
        val one = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = DiscreteAlmanacMap("source", "destination", List.empty)
        assert(one != two)
      }

      it("Different type") {
        val one = DiscreteAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        val two = RangedAlmanacMap("source", "destination", List(MapRange(3, 2, 1)))
        assert(one != two)
      }
    }
  }

  describe("mapSourceValues") {
    it("A map with no range should act as a pass through") {
      val almanac = DiscreteAlmanacMap("a", "b", List.empty)
      val longs = (0L to 100L).toList
      assert(almanac.mapSourceValues(longs) == longs)
    }

    it("A map that redefines a single value with one map") {
      val almanac = DiscreteAlmanacMap("a", "b", List(MapRange(45, 69, 1)))
      val expected = (0 to 68).toList.concat(List(45).concat((70 to 100).toList))
      assert(almanac.mapSourceValues((0L to 100L).toList) == expected)
    }

    it("A map that redefines a range of values with one map") {
      val almanac = DiscreteAlmanacMap("a", "b", List(MapRange(10, 60, 10)))
      val expected = (0 to 59).toList.concat((10 to 19).toList).concat((70 to 100).toList)
      assert(almanac.mapSourceValues((0L to 100L).toList) == expected)
    }

    it("A map with several maps within it") {
      val almanac = DiscreteAlmanacMap("a", "b", List(MapRange(90, 30, 5), MapRange(20, 80, 10)))
      val expected = (0 to 29).toList
        .concat((90 to 94).toList)
        .concat((35 to 79).toList)
        .concat((20 to 29).toList)
        .concat((90 to 100).toList)
      assert(almanac.mapSourceValues((0L to 100L).toList) == expected)
    }

    it("Lots of massive maps") {
      val inputs = List(3416930225L, 56865175, 4245248379L, 7142355, 1808166864, 294882110, 863761171, 233338109, 4114335326L, 67911591, 1198254212, 504239157, 3491380151L, 178996923, 3965970270L, 15230597, 2461206486L, 133606394, 2313929258L, 84595688)
      val almanac = DiscreteAlmanacMap("seed", "soil", List(
        MapRange(3534435790L, 4123267198L, 50004089),
        MapRange(3584439879L, 3602712894L, 238659237),
        MapRange(2263758314L, 0, 160870825),
        MapRange(2971481857L, 2850687195L, 31776688),
        MapRange(4173604159L, 3353763588L, 121363137),
        MapRange(3823099116L, 3003258545L, 350505043),
        MapRange(2850687195L, 2882463883L, 120794662),
        MapRange(1503174517, 2076905328, 347723811),
        MapRange(1850898328, 195477286, 412859986),
        MapRange(1265521310, 1606247567, 17062682),
        MapRange(3285153612L, 4173271287L, 121696009),
        MapRange(488201540, 828927797, 777319770),
        MapRange(453595079, 160870825, 34606461),
        MapRange(3406849621L, 3475126725L, 127586169),
        MapRange(1282583992, 608337272, 220590525),
        MapRange(3003258545L, 3841372131L, 281895067),
        MapRange(0, 1623310249, 453595079)
      ))

      val done = almanac.mapSourceValues(inputs)
    }
  }
}
