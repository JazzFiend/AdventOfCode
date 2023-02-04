package com.aoc

import com.aoc.CustomAssertions.Companion.assertRopeLocation
import org.junit.Ignore
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class PuzzleTest {
  @Nested
  class `Rope with two segments` {
    @Test
    fun `Given Example`() {
      val stringCommands = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2"
      )
      val ropeCommands = RopeCommandDecoder.generateCommandList(stringCommands)
      var rope = Rope()
      ropeCommands.forEach {
        rope = it.execute(rope)
      }
      assertRopeLocation(listOf(Pair(2, 2), Pair(1, 2)), rope)
      assertEquals(13, rope.tailLocationCount())
    }

    @Test
    fun `Puzzle 1`() {
      val stringCommands = ArrayList<String>()
      File("./src/test/kotlin/com/aoc/input.txt").forEachLine {
        stringCommands.add(it)
      }
      val ropeCommands = RopeCommandDecoder.generateCommandList(stringCommands)
      var rope = Rope()
      ropeCommands.forEach {
        rope = it.execute(rope)
      }
      assertEquals(6284, rope.tailLocationCount())
    }
  }

  @Nested
  class `Rope with ten segments` {
    @Test
    fun `Given Example 1`() {
      val stringCommands = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2"
      )
      val ropeCommands = RopeCommandDecoder.generateCommandList(stringCommands)
      var rope = Rope(10)
      ropeCommands.forEach {
        rope = it.execute(rope)
      }
      assertEquals(1, rope.tailLocationCount())
    }

    @Test
    fun `Given Example 2`() {
      val stringCommands = listOf(
        "R 5",
        "U 8",
        "L 8", // <---- This looks wrong. Segment 5 down should be a straight line below 4, but it is diagonal. 5 is in correct position but 6 on aren't.
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20"
      )
      val ropeCommands = RopeCommandDecoder.generateCommandList(stringCommands)
      var rope = Rope(10)
      ropeCommands.forEach {
        rope = it.execute(rope)
      }
      assertEquals(36, rope.tailLocationCount())
    }

    @Test
    fun `Puzzle 2`() {
      val stringCommands = ArrayList<String>()
      File("./src/test/kotlin/com/aoc/input.txt").forEachLine {
        stringCommands.add(it)
      }
      val ropeCommands = RopeCommandDecoder.generateCommandList(stringCommands)
      var rope = Rope(10)
      ropeCommands.forEach {
        rope = it.execute(rope)
      }
      assertEquals(2661, rope.tailLocationCount())
    }
  }
}
