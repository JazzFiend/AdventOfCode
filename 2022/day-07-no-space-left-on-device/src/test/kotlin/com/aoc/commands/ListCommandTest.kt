package com.aoc.commands

import com.aoc.TreeHelper.Companion.assertTreeCorrect
import com.aoc.models.FileNode
import com.aoc.models.FileTree
import org.junit.jupiter.api.Test

class ListCommandTest {
  @Test
  fun noItems() {
    val listCommand = ListCommand(listOf())
    var tree = FileTree()
    tree = listCommand.execute(tree)
    assertTreeCorrect(tree, "/", listOf(), listOf())
  }

  @Test
  fun oneFile() {
    val listCommand = ListCommand(listOf("1 a"))
    var tree = FileTree()
    tree = listCommand.execute(tree)
    assertTreeCorrect(tree, "/", listOf(), listOf(FileNode("a", 1)))
  }

  @Test
  fun multipleFilesOnSameNode() {
    val listCommand = ListCommand(listOf("1 a", "34 b", "222 c"))
    var tree = FileTree()
    val expectedFiles = listOf(FileNode("a", 1), FileNode("b", 34), FileNode("c", 222))
    tree = listCommand.execute(tree)
    assertTreeCorrect(tree, "/", listOf(), expectedFiles)
  }

  @Test
  fun oneDirectory() {
    val listCommand = ListCommand(listOf("dir z"))
    var tree = FileTree()
    tree = listCommand.execute(tree)
    assertTreeCorrect(tree, "/", listOf("z"), listOf())
  }

  @Test
  fun multipleDirectories() {
    val listCommand = ListCommand(listOf("dir z", "dir y", "dir x"))
    var tree = FileTree()
    tree = listCommand.execute(tree)
    assertTreeCorrect(tree, "/", listOf("z", "y", "x"), listOf())
  }

  @Test
  fun acceptanceTest() {
    val lsOutput = listOf("dir one", "55788 a.txt", "5464576 b.dat", "dir two")
    val listCommand = ListCommand(lsOutput)
    var tree = FileTree()
    val expectedFiles = listOf(FileNode("a.txt", 55788), FileNode("b.dat", 5464576))
    tree = listCommand.execute(tree)
    assertTreeCorrect(tree, "/", listOf("one", "two"), expectedFiles)
  }
}