package com.aoc

class ScenicScoreCalculator {
  companion object {
    fun calculateMaxScore(trees: List<List<Int>>): Int {
      if (trees[0].size <= 1) {
        return 0
      }
      var maxScenicScore = -1
      // May want to convert this to just a for loop
      trees.forEachIndexed { y, row ->
        trees[0].forEachIndexed { x, _ ->
          var scenicScore = 1
          scenicScore *= countTreesLeft(x, row)
          scenicScore *= countTreesUp(x, y, trees)
          scenicScore *= countTreesRight(x, row)
          scenicScore *= countTreesDown(x, y, trees)

          maxScenicScore = setMaxScore(scenicScore, maxScenicScore)
        }
      }
      return maxScenicScore
    }

    private fun setMaxScore(scenicScore: Int, maxScenicScore: Int): Int {
      if (scenicScore > maxScenicScore) {
        return scenicScore
      }
      return maxScenicScore
    }

    private fun countTreesRight(x: Int, row: List<Int>): Int {
      var treeCount = 0
      var counter = x + 1
      val tree = row[x]
      while (counter < row.size) {
        if (row[counter] >= tree) {
          return ++treeCount
        } else {
          treeCount++
          counter++
        }
      }
      return treeCount
    }

    private fun countTreesUp(x: Int, y: Int, trees: List<List<Int>>): Int {
      var treeCount = 0
      var counter = y - 1
      val tree = trees[y][x]
      while (counter >= 0) {
        if (trees[counter][x] >= tree) {
          return ++treeCount
        } else {
          treeCount++
          counter--
        }
      }
      return treeCount
    }

    private fun countTreesLeft(x: Int, row: List<Int>): Int {
      var treeCount = 0
      var counter = x - 1
      val tree = row[x]
      while (counter >= 0) {
        if (row[counter] >= tree) {
          return ++treeCount
        } else {
          treeCount++
          counter--
        }
      }
      return treeCount
    }

    private fun countTreesDown(x: Int, y: Int, trees: List<List<Int>>): Int {
      var treeCount = 0
      var counter = y + 1
      val tree = trees[y][x]
      while (counter < trees.size) {
        if (trees[counter][x] >= tree) {
          return ++treeCount
        } else {
          treeCount++
          counter++
        }
      }
      return treeCount
    }
  }
}
