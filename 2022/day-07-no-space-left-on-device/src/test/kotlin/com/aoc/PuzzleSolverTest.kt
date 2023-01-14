package com.aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class PuzzleSolverTest {
  @Test
  fun acceptanceTest() {
    val commands = listOf(
      "$ cd /",
      "$ ls",
      "dir a",
      "14848514 b.txt",
      "8504156 c.dat",
      "dir d",
      "$ cd a",
      "$ ls",
      "dir e",
      "29116 f",
      "2557 g",
      "62596 h.lst",
      "$ cd e",
      "$ ls",
      "584 i",
      "$ cd ..",
      "$ cd ..",
      "$ cd d",
      "$ ls",
      "4060174 j",
      "8033020 d.log",
      "5626152 d.ext",
      "7214296 k"
    )

    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    val smallFiles = directoryMap.filter { it.value <= 100000 }
    assertEquals(95437, smallFiles.values.reduce { acc, next -> acc + next})
  }

  @Test
  fun puzzleOne() {
    val commands = ArrayList<String>()
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine { commands.add(it) }
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    val smallFolders = directoryMap.filter { it.value <= 100000 }
    assertEquals(1644735, smallFolders.values.reduce { acc, next -> acc + next})
  }

  @Test
  fun puzzleTwo() {
    val commands = ArrayList<String>()
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine { commands.add(it) }
    val tree = FileTreeCommandInterpreter.createFileTree(commands)
    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)

    val totalSpace = 70000000
    val neededSpace = 30000000
    val usedSpace = directoryMap.getOrDefault("/", Int.MIN_VALUE)
    val unusedSpace = totalSpace - usedSpace
    val spaceToDelete = neededSpace - unusedSpace

    val largeFolders = directoryMap.filter { it.value >= spaceToDelete }
    val smallestDeletableFolder = largeFolders.minOf { it.value }
    assertEquals(1300850, smallestDeletableFolder)
  }
}