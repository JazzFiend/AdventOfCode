package com.aoc

import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestUtils {
  companion object {
    fun assertStackCorrect(stacks: List<Stack<Char>>, expected: List<String>) {
      assertTrue(stacks[0].isEmpty())
      assertEquals(expected.size, stacks.size - 1)
      for (i in IntRange(1, stacks.size - 1)) {
        assertEquals(expected[i - 1].length, stacks[i].size)
        for (j in IntRange(0, stacks[i].size - 1)) {
          assertEquals(expected[i - 1][j], stacks[i][j])
        }
      }
    }
  }
}