package com.aoc

import java.util.*

class StackCreator {
  companion object {
    fun create(stackText: String) : List<Stack<Char>> {
      val stacks = ArrayList<Stack<Char>>()
      val stackCount = countStacks(stackText)
      addOffsetStack(stacks)
      for(i in IntRange(0, stackCount - 1)) {
        stacks.add(extractNextStack(stackText, getCrateOffset(i)))
      }
      return stacks
    }

    private fun countStacks(stackText: String): Int {
      val stackTextSplit = splitStackText(stackText)
      return (stackTextSplit[0].split(" ").filter { it.isNotEmpty() }).size
    }

    private fun splitStackText(stackText: String) = stackText.split('\n').reversed()

    private fun addOffsetStack(stacks: ArrayList<Stack<Char>>) {
      stacks.add(Stack<Char>())
    }

    private fun getCrateOffset(i: Int) = (4 * i) + 1

    private fun extractNextStack(stackText: String, crateOffset: Int): Stack<Char> {
      val nextStack = Stack<Char>()
      val stackTextSplit = splitStackText(stackText)
      if (isStackEmpty(stackTextSplit)) { return nextStack }

      for(crate in stackTextSplit) {
        if(isCrate(crate[crateOffset])) {
          nextStack.add(crate[crateOffset])
        }
      }
      return nextStack
    }

    private fun isStackEmpty(stackTextSplit: List<String>) = stackTextSplit.size <= 1

    private fun isCrate(crate: Char) = !crate.isDigit() && crate != ' '
  }
}