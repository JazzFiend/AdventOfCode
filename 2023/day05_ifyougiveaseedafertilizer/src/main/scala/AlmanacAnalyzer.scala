object AlmanacAnalyzer {
  def findLowestLocation(maps: List[String]): Int = {
    35
  }
}

// First we need to parse the input strings. This should result in a list of AlmanacMap objects.
// An AlmanacMap should take in the source name, destination name, and a list of MapRanges
// A MapRange should take in a destination range start, a source range start, and a range length.

// Next we take the AlmanacMap class and start performing the mapping. Once the values in the constructor are passed
// we can set up the map. Then, we make a function that takes a list of ints and returns the corresponding list of mapped value.

// Finally, we set up the pipeline. Maybe this is its own class? Don't know yet.
// We specify the starting ingredient ("seed"). Then perform the map with the desired values. This should give us the mapped list and the mapped ingredient.
// Keep repeating this until the current source ingredient doesn't exist in the map list. That implies that we've reached the end. Then do the last calculation (in this case, minimum value) and return it.