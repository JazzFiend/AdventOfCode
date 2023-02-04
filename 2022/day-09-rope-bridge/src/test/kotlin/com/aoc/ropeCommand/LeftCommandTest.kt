package com.aoc.ropeCommand

import com.aoc.CustomAssertions.Companion.assertRopeLocation
import com.aoc.Rope
import org.junit.jupiter.api.Test

class LeftCommandTest {
  @Test
  fun `Move zero spaces`() {
    assertRopeLocation(listOf(Pair(0, 0), Pair(0, 0)), LeftCommand().execute(Rope()))
  }

  @Test
  fun `Move one space`() {
    assertRopeLocation(listOf(Pair(-1, 0), Pair(0, 0)), LeftCommand(1).execute(Rope()))
  }

  @Test
  fun `Move many spaces`() {
    assertRopeLocation(listOf(Pair(-8, 0), Pair(-7, 0)), LeftCommand(8).execute(Rope()))
  }
}