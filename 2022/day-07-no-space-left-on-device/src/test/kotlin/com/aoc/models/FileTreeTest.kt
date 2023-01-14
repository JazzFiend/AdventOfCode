package com.aoc.models

import com.aoc.exceptions.NoParentNodeException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class FileTreeTest {
  @Test
  fun defaultState() {
    val fileTree = FileTree()
    assertEquals("/", fileTree.getCurrentFolder())
  }

  @Test
  fun addFolderToCurrent() {
    val fileTree = FileTree()
    fileTree.addFolderToCurrent("B")
    assertEquals("/", fileTree.getCurrentFolder())
    assertEquals(listOf("B"), fileTree.getFolders())
  }

  @Test
  fun addFileToCurrent() {
    val fileTree = FileTree()
    fileTree.addFileToCurrent("file1", 7)
    fileTree.addFileToCurrent("file2", 98)
    assertEquals("/", fileTree.getCurrentFolder())
    assertEquals(
      listOf(
        FileNode("file1", 7),
        FileNode("file2", 98)
      ),
      fileTree.getCurrentFiles()
    )
  }

  @Nested
  class MoveCurrent {
    @Test
    fun moveCurrentToChild() {
      val fileTree = FileTree()
      fileTree.addFolderToCurrent("next")
      fileTree.moveTo("next")
      assertEquals("next", fileTree.getCurrentFolder())
    }

    @Test
    fun moveCurrentToNonExistentChild() {
      assertThrows<RuntimeException> {
        val fileTree = FileTree()
        fileTree.addFolderToCurrent("next")
        fileTree.moveTo("notNext")
      }
    }

    @Test
    fun moveCurrentToParent() {
      val fileTree = FileTree()
      fileTree.addFolderToCurrent("next")
      fileTree.moveTo("next")
      fileTree.moveToParent()
      assertEquals("/", fileTree.getCurrentFolder())
    }

    @Test
    fun moveCurrentToNonExistentParent() {
      assertThrows<NoParentNodeException> {
        val fileTree = FileTree()
        fileTree.moveToParent()
      }
    }

    @Test
    fun moveCurrentToRoot() {
      val fileTree = FileTree()
      fileTree.addFolderToCurrent("next")
      fileTree.moveTo("next")
        fileTree.addFolderToCurrent("nextAgain")
      fileTree.moveTo("nextAgain")
      fileTree.moveToRoot()
      assertEquals("/", fileTree.getCurrentFolder())
    }
  }
}
