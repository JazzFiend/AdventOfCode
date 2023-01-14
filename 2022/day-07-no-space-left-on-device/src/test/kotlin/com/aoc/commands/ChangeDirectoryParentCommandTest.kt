package com.aoc.commands

import com.aoc.TreeHelper.Companion.assertTreeCorrect
import com.aoc.models.FileTree
import org.junit.jupiter.api.Test

class ChangeDirectoryParentCommandTest {
  @Test
  fun moveOut() {
    val cdCommand = ChangeDirectoryParentCommand()
    val oldTree = FileTree()
    oldTree.addFolderToCurrent("next")
    oldTree.moveTo("next")
    oldTree.addFolderToCurrent("nextAgain")
    oldTree.moveTo("nextAgain")
    val newTree = cdCommand.execute(oldTree)
    assertTreeCorrect(newTree, "next", listOf("nextAgain"), listOf())
  }
}