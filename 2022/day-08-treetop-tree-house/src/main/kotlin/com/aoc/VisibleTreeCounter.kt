package com.aoc

class VisibleTreeCounter {
  companion object {
    fun countVisibleTrees(trees: List<List<Int>>): Int {
      var visibleCount = 0
      trees.forEachIndexed { y, row ->
        row.forEachIndexed { x, tree ->
          if (isOutsidePerimeter(y, x, row.size - 1, trees.size - 1) ||
            isVisibleFromLeft(x, tree, row) ||
            isVisibleFromTop(x, y, tree, trees) ||
            isVisibleFromRight(x, tree, row) ||
            isVisibleFromBottom(x, y, tree, trees)
          ) {
            visibleCount++
          }
        }
      }
      return visibleCount
    }

    private fun isOutsidePerimeter(
      y: Int,
      x: Int,
      maxX: Int,
      maxY: Int
    ) = y == 0 || x == 0 || y == maxY || x == maxX

    private fun isVisibleFromLeft(x: Int, tree: Int, row: List<Int>): Boolean {
      var counter = x - 1
      while (counter >= 0) {
        if (row[counter] >= tree) { return false }
        counter--
      }
      return true
    }

    private fun isVisibleFromTop(x: Int, y: Int, tree: Int, trees: List<List<Int>>): Boolean {
      var counter = y - 1
      while (counter >= 0) {
        if (trees[counter][x] >= tree) { return false }
        counter--
      }
      return true
    }

    private fun isVisibleFromRight(x: Int, tree: Int, row: List<Int>): Boolean {
      var counter = x + 1
      while (counter <= row.size - 1) {
        if (row[counter] >= tree) { return false }
        counter++
      }
      return true
    }

    private fun isVisibleFromBottom(x: Int, y: Int, tree: Int, trees: List<List<Int>>): Boolean {
      var counter = y + 1
      while (counter <= trees.size - 1) {
        if (trees[counter][x] >= tree) { return false }
        counter++
      }
      return true
    }
  }
}