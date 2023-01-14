package com.aoc.commands

import com.aoc.models.FileTree

abstract class FileTreeCommand {
  abstract fun execute(tree: FileTree): FileTree
}