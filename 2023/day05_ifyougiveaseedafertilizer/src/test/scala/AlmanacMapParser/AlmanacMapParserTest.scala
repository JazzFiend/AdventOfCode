package AlmanacMapParser

import AlmanacMap.{DiscreteAlmanacMap, RangedAlmanacMap}
import MapRange.MapRange
import org.scalatest.funspec.AnyFunSpec

class AlmanacMapParserTest extends AnyFunSpec {
  val discreteParser = new DiscreteAlmanacMapParser
  val rangedParser = new RangedAlmanacMapParser
  describe("Error cases") {
    it("An empty almanac should give an empty list of entries") {
      assertThrows[RuntimeException] {
        discreteParser.parseMapsDiscrete(List.empty)
      }
    }

    it("An almanac with just seeds should result in an empty list") {
      val justSeeds = List("seeds: 1 4 6 7")
      assertThrows[RuntimeException] {
        discreteParser.parseMapsDiscrete(justSeeds)
      }
    }

    it("Titles without dashes should throw") {
      val noDashesTitle = List(
        "seeds: 79 14 55 13",
        "incorrect map:",
        "1 2 4",
      )
      assertThrows[RuntimeException] {
        discreteParser.parseMapsDiscrete(noDashesTitle)
      }
    }

    it("Incorrectly formatted map ranges should throw") {
      val badRange = List(
        "seeds: 79 14 55 13",
        "first-to-second map:",
        "1 2 3 4"
      )
      assertThrows[RuntimeException] {
        discreteParser.parseMapsDiscrete(badRange)
      }
    }
  }

  it("An almanac with one correctly formatted map should create it successfully") {
    val oneMap = List(
      "seeds: 79 14 55 13",
      "first-to-second map:",
      "1 2 4",
    )
    val expectedDiscrete = List(DiscreteAlmanacMap("first", "second", List(MapRange(1, 2, 4))))
    val expectedRanged = List(RangedAlmanacMap("first", "second", List(MapRange(1, 2, 4))))

    assert(discreteParser.parseMapsDiscrete(oneMap) == expectedDiscrete)
    assert(rangedParser.parseMapsRanged(oneMap) == expectedRanged)
  }

  it("One map with many ranges should be created correctly") {
    val oneMapManyRanges = List(
      "seeds: 1 2 3 4",
      "a-to-b map:",
      "2 5 12",
      "3 5 1",
      "11 100 20"
    )
    val expectedDiscreteMapRanges = List(MapRange(2, 5, 12), MapRange(3, 5, 1), MapRange(11, 100, 20))
    val expectedDiscrete = List(DiscreteAlmanacMap("a", "b", expectedDiscreteMapRanges))

    val expectedRangedMapRanges = List(MapRange(2, 5, 12), MapRange(3, 5, 1), MapRange(11, 100, 20))
    val expectedRanged = List(RangedAlmanacMap("a", "b", expectedRangedMapRanges))

    assert(discreteParser.parseMapsDiscrete(oneMapManyRanges) == expectedDiscrete)
    assert(rangedParser.parseMapsRanged(oneMapManyRanges) == expectedRanged)
  }

  it("Many maps should be created correctly") {
    val manyMaps = List(
      "seeds: 1 2 3 4",
      "a-to-b map:",
      "2 5 12",
      "3 5 1",
      "11 100 20",
      "b-to-c map:",
      "48 5 66",
      "12 1 4"
    )
    val expectedDiscrete = List(
      DiscreteAlmanacMap("a", "b", List(
        MapRange(2, 5, 12),
        MapRange(3, 5, 1),
        MapRange(11, 100, 20),
      )),
      DiscreteAlmanacMap("b", "c", List(
        MapRange(48, 5, 66),
        MapRange(12, 1, 4)
      ))
    )
    val expectedRanged = List(
      RangedAlmanacMap("a", "b", List(
        MapRange(2, 5, 12),
        MapRange(3, 5, 1),
        MapRange(11, 100, 20),
      )),
      RangedAlmanacMap("b", "c", List(
        MapRange(48, 5, 66),
        MapRange(12, 1, 4)
      ))
    )

    assert(discreteParser.parseMapsDiscrete(manyMaps) == expectedDiscrete)
    assert(rangedParser.parseMapsRanged(manyMaps) == expectedRanged)
  }
}
