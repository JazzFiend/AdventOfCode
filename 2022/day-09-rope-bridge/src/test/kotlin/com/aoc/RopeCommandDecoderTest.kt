package com.aoc

import com.aoc.ropeCommand.DownCommand
import com.aoc.ropeCommand.LeftCommand
import com.aoc.ropeCommand.RightCommand
import com.aoc.ropeCommand.UpCommand
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RopeCommandDecoderTest {
  @Test
  fun `Empty set of commands`() {
    val commands = RopeCommandDecoder.generateCommandList(ArrayList())
    assertTrue(commands.isEmpty())
  }

  @Nested
  class `One instruction` {
    @Test
    fun right() {
      val commands = RopeCommandDecoder.generateCommandList(listOf("R 1"))
      assertTrue(commands[0] is RightCommand)
    }

    @Test
    fun up() {
      val commands = RopeCommandDecoder.generateCommandList(listOf("U 1"))
      assertTrue(commands[0] is UpCommand)
    }

    @Test
    fun left() {
      val commands = RopeCommandDecoder.generateCommandList(listOf("L 1"))
      assertTrue(commands[0] is LeftCommand)
    }

    @Test
    fun down() {
      val commands = RopeCommandDecoder.generateCommandList(listOf("D 1"))
      assertTrue(commands[0] is DownCommand)
    }
  }

  @Test
  fun `Multiple instructions`() {
    val stringCommands = listOf("R 43", "U 34", "D 5", "L 5")
    val commands = RopeCommandDecoder.generateCommandList(stringCommands)
    assertEquals(4, commands.size)
  }
}