import org.scalatest.funspec.AnyFunSpec

class RangedSeedParserTest extends AnyFunSpec {
  describe("parseSeedsAsRange") {
    it("One pair") {
      val onePair = List("seeds: 23 5")
      val expected = List((23, 27))
      assert(RangedSeedParser.parseSeedsAsRange(onePair) == expected)
    }

    it("Many pairs") {
      val manyPairs = List("seeds: 0 5 10 2 100 10")
      val expected = List((0, 4), (10, 11), (100, 109))
      assert(RangedSeedParser.parseSeedsAsRange(manyPairs) == expected)
    }

    it("Huge number of seeds") {
      val lotsOfSeeds = List("seeds: 1000000000 10000001 2000000000 10000001 3000000000 10000001 4000000000 10000001 5000000000 10000001 6000000000 10000001 7000000000 10000001 8000000000 10000001 9000000000 10000001 0 10000001")
      val expected = List(
        (1000000000L, 1010000000L),
        (2000000000L, 2010000000L),
        (3000000000L, 3010000000L),
        (4000000000L, 4010000000L),
        (5000000000L, 5010000000L),
        (6000000000L, 6010000000L),
        (7000000000L, 7010000000L),
        (8000000000L, 8010000000L),
        (9000000000L, 9010000000L),
        (0L, 10000000L),
      )
      assert(RangedSeedParser.parseSeedsAsRange(lotsOfSeeds) == expected)
    }
  }
}
