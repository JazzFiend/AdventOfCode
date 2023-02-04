package com.aoc

class RopeMover {
  companion object {
    fun moveRight(rope: Rope): Rope {
      return move(rope, 1, 0)
    }

    fun moveDown(rope: Rope): Rope {
      return move(rope, 0, -1)
    }

    fun moveLeft(rope: Rope): Rope {
      return move(rope, -1, 0)
    }

    fun moveUp(rope: Rope): Rope {
      return move(rope, 0, 1)
    }

    private fun move(rope: Rope, xOffset: Int, yOffset: Int): Rope {
      val newSegments = ArrayList<Pair<Int, Int>>()
      rope.ropeSegments.forEachIndexed { i, segment ->
        if (i == 0) {
          newSegments.add(moveSectionOffset(segment, xOffset, yOffset))
        } else {
          if (isNotAdjacent(newSegments[i - 1], segment)) {
            newSegments.add(
              calculateNextPoint(
                rope.ropeSegments[i - 1],
                newSegments[i - 1],
                segment
              )
            )
          } else {
            newSegments.add(segment)
          }
        }
      }

      rope.moveSegments(newSegments)
      return rope
    }

    private fun moveSectionOffset(
      initial: Pair<Int, Int>,
      xOffset: Int,
      yOffset: Int
    ): Pair<Int, Int> {
      return Pair(initial.first + xOffset, initial.second + yOffset)
    }

    private fun isNotAdjacent(previous: Pair<Int, Int>, current: Pair<Int, Int>): Boolean {
      return (!isPreviousOnCurrent(previous, current) &&
              !isPreviousBesideCurrent(previous, current) &&
              !isPreviousDiagonalToCurrent(previous, current))
    }

    private fun isPreviousOnCurrent(previous: Pair<Int, Int>, current: Pair<Int, Int>) =
      previous == current

    private fun isPreviousBesideCurrent(previous: Pair<Int, Int>, next: Pair<Int, Int>): Boolean {
      val xPrevious = previous.first
      val yPrevious = previous.second
      val xCurrent = next.first
      val yCurrent = next.second
      return (xPrevious == xCurrent + 1 && yPrevious == yCurrent) ||
              (xPrevious == xCurrent - 1 && yPrevious == yCurrent) ||
              (yPrevious == yCurrent + 1 && xPrevious == xCurrent) ||
              (yPrevious == yCurrent - 1 && xPrevious == xCurrent)
    }

    private fun isPreviousDiagonalToCurrent(
      previous: Pair<Int, Int>,
      current: Pair<Int, Int>
    ): Boolean {
      val xPrevious = previous.first
      val yPrevious = previous.second
      val xCurrent = current.first
      val yCurrent = current.second
      return (xPrevious == xCurrent + 1 && yPrevious == yCurrent + 1) ||
              (xPrevious == xCurrent + 1 && yPrevious == yCurrent - 1) ||
              (xPrevious == xCurrent - 1 && yPrevious == yCurrent + 1) ||
              (xPrevious == xCurrent - 1 && yPrevious == yCurrent - 1)
    }

    // This function (and class, I guess) is an absolute disaster, but I'm too burnt out on this
    // problem to care at this point.
    private fun calculateNextPoint(
      previousStart: Pair<Int, Int>,
      previousEnd: Pair<Int, Int>,
      currentSegment: Pair<Int, Int>
    ): Pair<Int, Int> {
      return if (isPreviousRightAndUp(previousStart, previousEnd)) {
        if (previousEnd.first == currentSegment.first) {
          Pair(currentSegment.first, currentSegment.second + 1)
        } else if (previousEnd.second == currentSegment.second) {
          Pair(currentSegment.first + 1, currentSegment.second)
        } else {
          Pair(currentSegment.first + 1, currentSegment.second + 1)
        }
      } else if (isPreviousRightAndDown(previousStart, previousEnd)) {
        if (previousEnd.first == currentSegment.first) {
          Pair(currentSegment.first, currentSegment.second - 1)
        } else if (previousEnd.second == currentSegment.second) {
          Pair(currentSegment.first + 1, currentSegment.second)
        } else {
          Pair(currentSegment.first + 1, currentSegment.second - 1)
        }
      } else if (isPreviousLeftAndUp(previousStart, previousEnd)) {
        if (previousEnd.first == currentSegment.first) {
          Pair(currentSegment.first, currentSegment.second + 1)
        } else if (previousEnd.second == currentSegment.second) {
          Pair(currentSegment.first - 1, currentSegment.second)
        } else {
          Pair(currentSegment.first - 1, currentSegment.second + 1)
        }
      } else if (isPreviousLeftAndDown(previousStart, previousEnd)) {
        if (previousEnd.first == currentSegment.first) {
          Pair(currentSegment.first, currentSegment.second - 1)
        } else if (previousEnd.second == currentSegment.second) {
          Pair(currentSegment.first - 1, currentSegment.second)
        } else {
          Pair(currentSegment.first - 1, currentSegment.second - 1)
        }
      } else {
        previousStart
      }
    }

    private fun isPreviousLeftAndDown(
      previousStart: Pair<Int, Int>,
      previousEnd: Pair<Int, Int>
    ) =
      previousStart.first - 1 == previousEnd.first && previousStart.second - 1 == previousEnd.second

    private fun isPreviousLeftAndUp(
      previousStart: Pair<Int, Int>,
      previousEnd: Pair<Int, Int>
    ) =
      previousStart.first - 1 == previousEnd.first && previousStart.second + 1 == previousEnd.second

    private fun isPreviousRightAndDown(
      previousStart: Pair<Int, Int>,
      previousEnd: Pair<Int, Int>
    ) =
      previousStart.first + 1 == previousEnd.first && previousStart.second - 1 == previousEnd.second

    private fun isPreviousRightAndUp(
      previousStart: Pair<Int, Int>,
      previousEnd: Pair<Int, Int>
    ) =
      previousStart.first + 1 == previousEnd.first && previousStart.second + 1 == previousEnd.second
  }
}
