package com.aoc

import com.aoc.models.FileTree
import com.aoc.models.FileTreeNode

class FileSummer {
  companion object {
    fun calculateFileSizePerDirectory(tree: FileTree): Map<String, Int> {
      if (tree.getCurrentFiles().isEmpty() && tree.getFolders().isEmpty()) {
        return mapOf(Pair("/", 0))
      }
      return recursiveFunction(tree.getRoot())
    }

    private fun sumFiles(node: FileTreeNode): Int {
      if(node.getFiles().isEmpty()) { return 0 }
      return node.getFiles().map { it.fileSize }.reduce { acc, fileSize -> acc + fileSize }
    }

    private fun recursiveFunction(node: FileTreeNode): Map<String, Int> {
      val directorySizeMap = setDefaultMap(node)
      var totalSize = 0
      for (folder in node.getChildren()) {
        val childFolders = recursiveFunction(folder)
        totalSize += childFolders.getOrDefault(folder.getName(), Integer.MAX_VALUE)
        for (childFolder in childFolders) {
          val folderName = replaceFolderNameIfCopy(directorySizeMap, childFolder.key)
          directorySizeMap[folderName] =
            childFolders.getOrDefault(childFolder.key, Integer.MAX_VALUE)
        }
      }
      totalSize += sumFiles(node)
      directorySizeMap[node.getName()] = totalSize
      return directorySizeMap
    }

    private fun setDefaultMap(node: FileTreeNode): MutableMap<String, Int> {
      val directorySizeMap = HashMap<String, Int>()
      directorySizeMap[node.getName()] = 0
      return directorySizeMap
    }

    private fun replaceFolderNameIfCopy(directoryMap: Map<String, Int>, folderName: String): String {
      var updatedFolderName = folderName
      while(directoryMap.contains(updatedFolderName)) {
        updatedFolderName += "-copy"
      }
      return updatedFolderName
    }
  }
}
