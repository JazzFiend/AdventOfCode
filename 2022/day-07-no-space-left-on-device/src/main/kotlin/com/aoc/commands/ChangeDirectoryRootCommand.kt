package com.aoc.commands

import com.aoc.models.FileTree

class ChangeDirectoryRootCommand : FileTreeCommand() {
  override fun execute(tree: FileTree): FileTree {
    tree.moveToRoot()
    return tree
  }

  override fun equals(other: Any?): Boolean {
    return other is ChangeDirectoryRootCommand
  }

  override fun hashCode(): Int {
    return javaClass.hashCode()
  }
}
