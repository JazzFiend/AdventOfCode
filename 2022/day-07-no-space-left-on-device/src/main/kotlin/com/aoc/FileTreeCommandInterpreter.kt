package com.aoc

import com.aoc.commands.CommandDecoder
import com.aoc.models.FileTree

class FileTreeCommandInterpreter {
  companion object {
    fun createFileTree(commands: List<String>): FileTree {
      var tree = FileTree()
      var i = 0

      while(i < commands.size) {
        val command = CommandDecoder.createCommand(commands, i)
        i = CommandDecoder.calculateIndexOffset(commands, i)
        tree = command.execute(tree)
      }
      return tree
    }
  }
}