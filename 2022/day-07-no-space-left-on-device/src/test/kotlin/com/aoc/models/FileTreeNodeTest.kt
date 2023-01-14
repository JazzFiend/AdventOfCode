package com.aoc.models

import com.aoc.exceptions.NoParentNodeException
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

class FileTreeNodeTest {
  @Nested
  class Constructor {
    @Test
    fun defaultConstructor() {
      assertThrows<NoParentNodeException> {
        val n = FileTreeNode("node")
        assertEquals("node", n.getName())
        assertEquals(listOf(), n.getChildren())
        assertEquals(listOf(), n.getFiles())
        n.getParent()
      }
    }

    @Test
    fun constructorWithParent() {
      val n = FileTreeNode("node", FileTreeNode("parent"))
      assertEquals("node", n.getName())
      assertEquals(listOf(), n.getChildren())
      assertEquals(listOf(), n.getFiles())
      assertEquals(FileTreeNode("parent"), n.getParent())
    }
  }

  @Nested
  class Equals {
    companion object {
      val original = FileTreeNode("O")

      @BeforeAll
      @JvmStatic
      internal fun beforeAll() {
        original.addChild("Child1")
        original.addFile("File1", 5000)
      }
    }

    @Test
    fun wrongType() {
      val other = ArrayList<String>()
      assertFalse(original.equals(other))
    }

    @Test
    fun wrongName() {
      val other = FileTreeNode("C")
      assertNotEquals(original, other)
    }

    @Test
    fun wrongNumberOfChildren() {
      val other = FileTreeNode("O")
      other.addChild("Child1")
      other.addChild("Child2")
      assertNotEquals(original, other)
    }

    @Test
    fun wrongChild() {
      val other = FileTreeNode("O")
      other.addChild("Child2")
      assertNotEquals(original, other)
    }

    @Test
    fun wrongNumberOfFiles() {
      val other = FileTreeNode("O")
      other.addChild("Child1")
      other.addFile("WrongFile", 5)
      assertNotEquals(original, other)
    }

    @Test
    fun wrongFile() {
      val other = FileTreeNode("O")
      other.addChild("Child1")
      other.addFile("File1", 5000)
      other.addFile("WrongFile", 5)
      assertNotEquals(original, other)
    }

    @Test
    fun equalNodes() {
      val other = FileTreeNode("O")
      other.addChild("Child1")
      other.addFile("File1", 5000)
      assertEquals(original, other)
    }
  }
}