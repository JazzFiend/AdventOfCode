import org.scalatest.funspec.AnyFunSpec

class AlmanacMapParserTest extends AnyFunSpec {
  describe("Error cases") {
    it("An empty almanac should give an empty list of entries") {
      assert(AlmanacMapParser.parseMaps(List.empty) == List.empty)
    }

    it("An almanac with just seeds should result in an empty list") {
      val justSeeds = List("seeds: 1 4 6 7")
      assert(AlmanacMapParser.parseMaps(justSeeds) == List.empty)
    }

    it("Titles without dashes should throw") {
      val noDashesTitle = List(
        "seeds: 79 14 55 13",
        "incorrect map:",
        "1 2 4",
      )
      assertThrows[RuntimeException] {
        AlmanacMapParser.parseMaps(noDashesTitle)
      }
    }

    it("Incorrectly formatted map ranges should throw") {
      val badRange = List(
        "seeds: 79 14 55 13",
        "first-to-second map:",
        "1 2 3 4"
      )
      assertThrows[RuntimeException] {
        AlmanacMapParser.parseMaps(badRange)
      }
    }
  }

  it("An almanac with one correctly formatted map should create it successfully") {
    val oneMap = List(
      "seeds: 79 14 55 13",
      "first-to-second map:",
      "1 2 4",
    )
    val expected = List(AlmanacMap("first", "second", List(MapRange(1, 2, 4))))
    assert(AlmanacMapParser.parseMaps(oneMap) == expected)
  }

  it("One map with many ranges should be created correctly") {
    val oneMapManyRanges = List(
      "seeds: 1 2 3 4",
      "a-to-b map:",
      "2 5 12",
      "3 5 1",
      "11 100 20"
    )
    val expectedMapRanges = List(MapRange(2, 5, 12), MapRange(3, 5, 1), MapRange(11, 100, 20))
    val expected = List(AlmanacMap("a", "b", expectedMapRanges))
    assert(AlmanacMapParser.parseMaps(oneMapManyRanges) == expected)
  }
}
