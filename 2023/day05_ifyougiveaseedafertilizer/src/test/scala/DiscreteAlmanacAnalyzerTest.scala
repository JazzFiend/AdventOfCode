import org.scalatest.funspec.AnyFunSpec

class DiscreteAlmanacAnalyzerTest extends AnyFunSpec {
  it("Acceptance Test") {
    val almanac = List(
      "seeds: 79 14 55 13",
      "seed-to-soil map:",
      "50 98 2",
      "52 50 48",
      "soil-to-fertilizer map:",
      "0 15 37",
      "37 52 2",
      "39 0 15",
      "fertilizer-to-water map:",
      "49 53 8",
      "0 11 42",
      "42 0 7",
      "57 7 4",
      "water-to-light map:",
      "88 18 7",
      "18 25 70",
      "light-to-temperature map:",
      "45 77 23",
      "81 45 19",
      "68 64 13",
      "temperature-to-humidity map:",
      "0 69 1",
      "1 0 69",
      "humidity-to-location map:",
      "60 56 37",
      "56 93 4"
    )
    assert(DiscreteAlmanacAnalyzer.findLowestLocation(almanac) == 35)
  }

  it("Puzzle 1") {
    val almanac = io.Source.fromFile("src/test/scala/input.txt").getLines
      .toList
      .filterNot(line => line.isEmpty)
    assert(DiscreteAlmanacAnalyzer.findLowestLocation(almanac) == 806029445)
  }
}

// Okay... Puzzle #2 is a doozy. Instead of providing a list of seeds, the input provides ranges of seeds.
// My initial solution wasn't a big change. Just turn the ranges into a list of seeds and just go ahead and run it through the same program.
// HOWEVER... The ranges are so huge that this doesn't run in a reasonable amount of time.
// Instead, we need to write a version of the program that ONLY deals with ranges. Trying to retrofit what we currently have was incredibly complex.
// I should write an acceptance test first and then start from scratch with the range requirement. Reuse what I can, but don't default to that.
