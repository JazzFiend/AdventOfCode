package com.aoc.ropeCommand

import com.aoc.CustomAssertions.Companion.assertRopeLocation
import com.aoc.Rope
import org.junit.jupiter.api.Test

class RightCommandTest {
  @Test
  fun `Move zero spaces`() {
    assertRopeLocation(listOf(Pair(0, 0), Pair(0, 0)), RightCommand().execute(Rope()))
  }

  @Test
  fun `Move one space`() {
    assertRopeLocation(listOf(Pair(1, 0), Pair(0, 0)), RightCommand(1).execute(Rope()))
  }

  @Test
  fun `Move many spaces`() {
    assertRopeLocation(listOf(Pair(4, 0), Pair(3, 0)), RightCommand(4).execute(Rope()))
  }
}