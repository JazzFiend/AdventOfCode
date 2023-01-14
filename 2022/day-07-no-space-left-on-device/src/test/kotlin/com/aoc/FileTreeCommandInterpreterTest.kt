package com.aoc

import com.aoc.TreeHelper.Companion.assertTreeCorrect
import com.aoc.models.FileNode
import org.junit.jupiter.api.Test

class FileTreeCommandInterpreterTest {
  @Test
  fun noCommands() {
    val tree = FileTreeCommandInterpreter.createFileTree(listOf())
    assertTreeCorrect(tree, "/", listOf(), listOf())
  }

  @Test
  fun oneLsCommandNoOutput() {
    val commands = listOf("$ ls")
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    assertTreeCorrect(tree, "/", listOf(), listOf())
  }

  @Test
  fun oneLsCommandSeveralOutputs() {
    val commands = listOf("$ ls", "34 g", "dir i", "888 n")
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    assertTreeCorrect(tree, "/", listOf("i"), listOf(FileNode("g", 34), FileNode("n", 888)))
  }

  @Test
  fun cdToFolder() {
    val commands = listOf("$ ls", "dir rf", "$ cd rf")
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    assertTreeCorrect(tree, "rf", listOf(), listOf())
  }

  @Test
  fun cdToRoot() {
    val commands = listOf(
      "$ ls",
      "dir gd",
      "4985 d",
      "$ cd gd",
      "$ ls",
      "dir bsf",
      "43 fff",
      "$ cd bsf",
      "$ cd /"
    )
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    assertTreeCorrect(tree, "/", listOf("gd"), listOf(FileNode("d", 4985)))
  }

  @Test
  fun cdToParent() {
    val commands = listOf(
      "$ ls",
      "dir gd",
      "4985 d",
      "$ cd gd",
      "$ ls",
      "dir bsf",
      "43 fff",
      "$ cd bsf",
      "$ cd .."
    )
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    assertTreeCorrect(tree, "gd", listOf("bsf"), listOf(FileNode("fff", 43)))
  }
}
