package com.aoc.commands

import com.aoc.models.FileTree

class ChangeDirectoryParentCommand : FileTreeCommand() {
  override fun execute(tree: FileTree): FileTree {
    tree.moveToParent()
    return tree
  }

  override fun equals(other: Any?): Boolean {
    if (other !is ChangeDirectoryParentCommand) { return false }
    return true
  }

  override fun hashCode(): Int {
    return javaClass.hashCode()
  }
}
