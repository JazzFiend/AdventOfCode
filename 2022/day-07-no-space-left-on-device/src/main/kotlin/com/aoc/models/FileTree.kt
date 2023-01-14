package com.aoc.models

class FileTree {
  private val root = FileTreeNode("/")
  private var current = root

  fun getCurrentFolder(): String {
    return current.getName()
  }

  fun getFolders(): List<String> {
    val children = current.getChildren()
    val folderList = ArrayList<String>()
    for(child in children) {
      folderList.add(child.getName())
    }
    return folderList
  }

  fun getCurrentFiles(): List<FileNode> {
    return current.getFiles()
  }

  fun getRoot(): FileTreeNode {
    return root
  }

  fun addFolderToCurrent(name: String) {
    current.addChild(name)
  }

  fun addFileToCurrent(name: String, fileSize: Int) {
    current.addFile(name, fileSize)
  }

  fun moveTo(folderName: String) {
    val children = current.getChildren()
    for(child in children) {
      if(folderName == child.getName()) {
        current = child
        return
      }
    }
    throw RuntimeException("$folderName does not exist")
  }

  fun moveToParent() {
    current = current.getParent()
  }

  fun moveToRoot() {
    current = root
  }
}