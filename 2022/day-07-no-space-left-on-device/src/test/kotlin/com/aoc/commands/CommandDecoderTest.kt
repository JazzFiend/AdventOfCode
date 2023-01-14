package com.aoc.commands

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// The same code is run for both the command and the incremanter, so the test cases are the same. I
// combined all of them here.
class CommandDecoderTest {
  @Nested
  class LsCommand {
    @Test
    fun listInstructionWithNoOutput() {
      val commands = listOf("$ ls")
      val location = 0
      assertEquals(ListCommand(listOf()), CommandDecoder.createCommand(commands, location))
      assertEquals(1, CommandDecoder.calculateIndexOffset(commands, location))
    }

    @Test
    fun listInstructionOneOutput() {
      val commands = listOf("$ ls", "8748 i.txt")
      val location = 0
      assertEquals(
        ListCommand(listOf("8748 i.txt")),
        CommandDecoder.createCommand(commands, location)
      )
      assertEquals(2, CommandDecoder.calculateIndexOffset(commands, location))
    }

    @Test
    fun listInstructionManyOutputs() {
      val commands = listOf("$ ls", "1252 doink.txt", "dir v", "dir nope")
      val location = 0
      assertEquals(
        ListCommand(listOf("1252 doink.txt", "dir v", "dir nope")),
        CommandDecoder.createCommand(commands, location)
      )
      assertEquals(4, CommandDecoder.calculateIndexOffset(commands, location))
    }

    @Test
    fun listInstructionWithInstructionAfter() {
      val commands = listOf("$ ls", "1252 doink.txt", "dir v", "$ cd v")
      val location = 0
      assertEquals(
        ListCommand(listOf("1252 doink.txt", "dir v")),
        CommandDecoder.createCommand(commands, location)
      )
      assertEquals(3, CommandDecoder.calculateIndexOffset(commands, location))
    }

    @Test
    fun listInstructionInMiddle() {
      val commands = listOf("$ ls", "dir eee", "$ ls", "dir rrr")
      val location = 2
      assertEquals(ListCommand(listOf("dir rrr")), CommandDecoder.createCommand(commands, location))
      assertEquals(4, CommandDecoder.calculateIndexOffset(commands, location))
    }
  }

  @Test
  fun cdInstructionByName() {
    val commands = listOf("$ cd www")
    assertEquals(ChangeDirectoryByNameCommand("www"), CommandDecoder.createCommand(commands, 0))
    assertEquals(1, CommandDecoder.calculateIndexOffset(commands, 0))
  }

  @Test
  fun cdInstructionParent() {
    val commands = listOf("$ cd ..")
    assertEquals(ChangeDirectoryParentCommand(), CommandDecoder.createCommand(commands, 0))
    assertEquals(1, CommandDecoder.calculateIndexOffset(commands, 0))
  }

  @Test
  fun cdInstructionRoot() {
    val commands = listOf("$ cd /")
    assertEquals(ChangeDirectoryRootCommand(), CommandDecoder.createCommand(commands, 0))
    assertEquals(1, CommandDecoder.calculateIndexOffset(commands, 0))
  }
}
