package com.aoc.models

import com.aoc.exceptions.NoParentNodeException

class FileTreeNode(private var name: String, private val parent: FileTreeNode? = null) {
  private val children = ArrayList<FileTreeNode>()
  private val files = ArrayList<FileNode>()

  fun addChild(name: String) {
    children.add(FileTreeNode(name, this))
  }

  fun addFile(name: String, size: Int) {
    files.add(FileNode(name, size))
  }

  fun getName(): String {
    return name
  }

  fun getChildren(): List<FileTreeNode> {
    return children
  }

  fun getFiles(): List<FileNode> {
    return files
  }

  fun getParent(): FileTreeNode {
    if(parent == null) {
      throw NoParentNodeException()
    }
    return parent
  }

  override fun equals(other: Any?): Boolean {
    if(other !is FileTreeNode ||
       name != other.getName() ||
       children != other.getChildren() ||
       files != other.getFiles()) {
      return false
    }
    return true
  }

}