package com.aoc.models

import com.aoc.models.FileNode
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

class FileNodeTest {
  @Nested
  class Equals {
    private val original = FileNode("original", 100)

    @Test
    fun typeNotEqual() {
      val other = HashSet<String>()
      assertFalse(original.equals(other))
    }

    @Test
    fun fileNameNotEqual() {
      val other = FileNode("other", 100)
      assertNotEquals(original, other)
    }

    @Test
    fun fileSizeNotEqual() {
      val other = FileNode("original", 101)
      assertNotEquals(original, other)
    }

    @Test
    fun filesEqual() {
      val other = FileNode("original", 100)
      assertEquals(original, other)
    }
  }
}