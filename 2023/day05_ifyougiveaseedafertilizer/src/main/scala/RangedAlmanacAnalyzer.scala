object RangedAlmanacAnalyzer {
  def findLowestLocation(almanac: List[String]): Long = {
    val seeds = SeedParser.parseRangedSeeds(almanac)
    val almanacMaps = AlmanacMapParser.parseMapsRanged(almanac)
    35
  }
}

// Original Algorithm:
// Convert first line of seeds into list of numbers
// Convert other lines into List of AlmanacMaps
// Send seeds into pipeline. Convert source numbers into mapped numbers until all AlmanacMaps have been used.


// New Algorithm:
// Convert first line of seeds into a list of ranges.
// Convert the other lines into a different kind of AlmanacMap. This one has to map a range of values.
//  - This is the meat of the program and where I got tripped up last time. Mapping ranges in a functional way is tough.
//    I should start here and try to come up with a test for every possible map type. If I have to start non functional, so be it.
//    I may want to also have a custom range class. The _1 and _2 identifiers are pretty unreadable.
// Send the seeds into the pipeline like before.