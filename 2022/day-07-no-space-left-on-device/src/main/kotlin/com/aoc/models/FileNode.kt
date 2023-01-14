package com.aoc.models

class FileNode(val fileName: String, val fileSize: Int) {
  override fun equals(other: Any?): Boolean {
    return !(other !is FileNode ||
            fileName != other.fileName ||
            fileSize != other.fileSize)
  }
}