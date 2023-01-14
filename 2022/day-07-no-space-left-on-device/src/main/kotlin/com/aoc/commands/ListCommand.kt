package com.aoc.commands

import com.aoc.models.FileTree

class ListCommand(val lsOutput: List<String>) : FileTreeCommand() {
  override fun execute(tree: FileTree): FileTree {
    for (item in lsOutput) {
      if (isDirectory(item)) {
        val fileName = extractFolderArguments(item)
        tree.addFolderToCurrent(fileName)
      } else {
        val fileArgs = extractFileArguments(item)
        tree.addFileToCurrent(fileArgs.first, fileArgs.second)
      }
    }
    return tree
  }

  override fun equals(other: Any?): Boolean {
    return !(other !is ListCommand ||
            lsOutput != other.lsOutput)
  }

  private fun extractFolderArguments(command: String): String {
    return command.split(" ")[1]
  }

  private fun isDirectory(command: String): Boolean {
    val tokenizedCommand = command.split(" ")
    return tokenizedCommand[0] == "dir"
  }

  private fun extractFileArguments(command: String): Pair<String, Int> {
    val fileArgs = command.split(" ")
    return Pair(fileArgs[1], fileArgs[0].toInt())
  }

  override fun hashCode(): Int {
    return lsOutput.hashCode()
  }
}
