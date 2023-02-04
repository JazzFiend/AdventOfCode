package com.aoc.ropeCommand

import com.aoc.CustomAssertions.Companion.assertRopeLocation
import com.aoc.Rope
import org.junit.jupiter.api.Test

class UpCommandTest {
  @Test
  fun `Move zero spaces`() {
    assertRopeLocation(listOf(Pair(0, 0), Pair(0, 0)), UpCommand().execute(Rope()))
  }

  @Test
  fun `Move one space`() {
    assertRopeLocation(listOf(Pair(0, 1), Pair(0, 0)), UpCommand(1).execute(Rope()))
  }

  @Test
  fun `Move many spaces`() {
    assertRopeLocation(listOf(Pair(0, 2), Pair(0, 1)), UpCommand(2).execute(Rope()))
  }
}