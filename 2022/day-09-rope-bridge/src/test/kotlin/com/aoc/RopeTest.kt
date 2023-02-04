package com.aoc

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RopeTest {
  private lateinit var rope: Rope

  @BeforeEach
  fun setup() {
    rope = Rope()
  }

  @Test
  fun `On creation, the tail location count should be 1`() {
    assertEquals(1, rope.tailLocationCount())
  }

  @Test
  fun `Moving the head should have no effect on tail location count`() {
    rope.moveSegments(listOf(Pair(4, 5), Pair(0, 0)))
    assertEquals(1, rope.tailLocationCount())
  }

  @Test
  fun `When the tail moves to a unique location, we increment tail location count`() {
    rope.moveSegments(listOf(Pair(0, 0), Pair(78, 4)))
    rope.moveSegments(listOf(Pair(0, 0), Pair(9, 8)))
    assertEquals(3, rope.tailLocationCount())
  }

  @Test
  fun `When the tail moves to a location we've been to, don't increment tail location count`() {
    rope.moveSegments(listOf(Pair(0, 0), Pair(1, 1)))
    rope.moveSegments(listOf(Pair(0, 0), Pair(0, 0)))
    rope.moveSegments(listOf(Pair(0, 0), Pair(1, 1)))
    assertEquals(2, rope.tailLocationCount())
  }

  @Test
  fun `Move a rope with multiple sections`() {
    val rope = Rope(9)
    val newLocations = listOf(Pair(1, 1), Pair(2, 2), Pair(3, 3), Pair(4, 4), Pair(5, 5), Pair(6, 6), Pair(7, 7), Pair(8, 8), Pair(9, 9))
    rope.moveSegments(newLocations)
    assertEquals(newLocations, rope.ropeSegments)
  }
}
