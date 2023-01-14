package com.aoc

import com.aoc.models.FileNode
import com.aoc.models.FileTree
import kotlin.test.assertEquals

class TreeHelper {
  companion object {
    fun assertTreeCorrect(
      tree: FileTree,
      current: String,
      folderList: List<String>,
      fileList: List<FileNode>
    ) {
      assertEquals(current, tree.getCurrentFolder())
      assertEquals(folderList, tree.getFolders())
      assertEquals(fileList, tree.getCurrentFiles())
    }
  }
}