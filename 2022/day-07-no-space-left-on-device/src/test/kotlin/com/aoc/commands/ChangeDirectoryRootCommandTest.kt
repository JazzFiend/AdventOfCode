package com.aoc.commands

import com.aoc.TreeHelper
import com.aoc.models.FileTree
import org.junit.jupiter.api.Test

class ChangeDirectoryRootCommandTest {
  @Test
  fun moveToRoot() {
    val cdCommand = ChangeDirectoryRootCommand()
    val oldTree = FileTree()
    oldTree.addFolderToCurrent("next")
    oldTree.moveTo("next")
    oldTree.addFolderToCurrent("nextAgain")
    oldTree.moveTo("nextAgain")
    val newTree = cdCommand.execute(oldTree)
    TreeHelper.assertTreeCorrect(newTree, "/", listOf("next"), listOf())
  }
}