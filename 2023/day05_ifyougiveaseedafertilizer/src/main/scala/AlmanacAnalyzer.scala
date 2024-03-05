object AlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    val seeds = SeedParser.parseSeeds(almanac)
    val maps = AlmanacMapParser.parseMaps(almanac)
    val finalValues = AlmanacPipeline.processPipeline(seeds, maps)
    finalValues.min
  }
}

// First we need to parse the input strings. This consists of two functions.
// (DONE) The first function is extracting the desired seeds. This is just a list of ints.
// (DONE) Second, we need the maps. This should result in a list of AlmanacMap objects.
// An AlmanacMap should take in the source name, destination name, and a list of MapRanges
// A MapRange should take in a destination range start, a source range start, and a range length.

// (DONE) Next we take the AlmanacMap class and start performing the mapping. Once the values in the constructor are passed
// we can set up the map. Then, we make a function that takes a list of ints and returns the corresponding list of mapped value.

// (DONE) Finally, we set up the pipeline. Maybe this is its own class? Don't know yet.
// We specify the starting ingredient ("seed"). Then perform the map with the desired values. This should give us the mapped list and the mapped ingredient.
// Keep repeating this until the current source ingredient doesn't exist in the map list. That implies that we've reached the end. Then do the last calculation (in this case, minimum value) and return it.

// Okay, this is all working, but its taking forever and we're blowing the heap space. Let's start by making an Almanac Map Test with one of the values from the puzzle.