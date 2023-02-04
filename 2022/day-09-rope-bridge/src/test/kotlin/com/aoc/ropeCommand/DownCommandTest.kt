package com.aoc.ropeCommand

import com.aoc.CustomAssertions.Companion.assertRopeLocation
import com.aoc.Rope
import org.junit.jupiter.api.Test

class DownCommandTest {
  @Test
  fun `Move zero spaces`() {
    assertRopeLocation(listOf(Pair(0, 0), Pair(0, 0)), DownCommand().execute(Rope()))
  }

  @Test
  fun `Move one space`() {
    assertRopeLocation(listOf(Pair(0, -1), Pair(0, 0)), DownCommand(1).execute(Rope()))
  }

  @Test
  fun `Move many spaces`() {
    assertRopeLocation(listOf(Pair(0, -9), Pair(0, -8)), DownCommand(9).execute(Rope()))
  }
}