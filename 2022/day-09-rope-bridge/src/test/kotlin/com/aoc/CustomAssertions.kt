package com.aoc

import kotlin.test.assertEquals

class CustomAssertions {
  companion object {
//    fun assertRopeLocation(head: Pair<Int, Int>, tail: Pair<Int, Int>, r: Rope) {
//      assertEquals(head, r.ropeSegments[0], "Head in wrong location")
//      assertEquals(tail, r.ropeSegments[1], "Tail in wrong location")
//    }

    fun assertRopeLocation(locations: List<Pair<Int, Int>>, r: Rope) {
      locations.forEachIndexed { i, location ->
        assertEquals(location, r.ropeSegments[i])
      }
    }
  }
}