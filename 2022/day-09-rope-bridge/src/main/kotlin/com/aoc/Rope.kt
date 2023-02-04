package com.aoc

class Rope(sectionCount: Int = 2) {
//  var headLocation: Pair<Int, Int> = Pair(0, 0)
//  var tailLocation: Pair<Int, Int> = Pair(0, 0)
//    set(value) {
//      field = value
//      tailVisitedPoints.add(value)
//    }
  private val tailVisitedPoints = HashSet<Pair<Int, Int>>()
  // ***** MAy need to make this private
  val ropeSegments = ArrayList<Pair<Int, Int>>()

  init {
    tailVisitedPoints.add(Pair(0, 0))
    IntRange(1, sectionCount).forEach { _ ->
      ropeSegments.add(Pair(0, 0))
    }
  }

  fun tailLocationCount():Int {
    return tailVisitedPoints.size
  }

  fun moveSegments(newLocations: List<Pair<Int, Int>>) {
    newLocations.forEachIndexed { i, location ->
      ropeSegments[i] = location
    }
    tailVisitedPoints.add(ropeSegments[ropeSegments.size - 1])
  }
}
