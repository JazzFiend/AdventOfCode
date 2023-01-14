package com.aoc.commands

import com.aoc.models.FileTree

class ChangeDirectoryByNameCommand(val directoryName: String) : FileTreeCommand() {
  override fun execute(tree: FileTree): FileTree {
    tree.moveTo(directoryName)
    return tree
  }

  override fun equals(other: Any?): Boolean {
    return !(other !is ChangeDirectoryByNameCommand ||
            directoryName != other.directoryName)
  }

  override fun hashCode(): Int {
    return directoryName.hashCode()
  }
}
