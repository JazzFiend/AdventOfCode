package com.aoc.commands

import com.aoc.TreeHelper.Companion.assertTreeCorrect
import com.aoc.models.FileTree
import org.junit.jupiter.api.Test

class ChangeDirectoryByNameCommandTest {
  @Test
  fun moveIn() {
    val cdCommand = ChangeDirectoryByNameCommand("change")
    val oldTree = FileTree()
    oldTree.addFolderToCurrent("change")
    val newTree = cdCommand.execute(oldTree)
    assertTreeCorrect(newTree, "change", listOf(), listOf())
  }
}