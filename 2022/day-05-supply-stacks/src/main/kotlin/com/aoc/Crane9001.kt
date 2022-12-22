package com.aoc

import java.util.*

class Crane9001 : Crane() {
  override fun executeInstruction(
    quantity: Int,
    crates: List<Stack<Char>>,
    destination: Int,
    source: Int
  ): List<Stack<Char>> {
    val tempStack = Stack<Char>()
    for (i in IntRange(1, quantity)) {
      tempStack.add(crates[source].pop())
    }
    while(tempStack.isNotEmpty()) {
      crates[destination].add(tempStack.pop())
    }
    return crates
  }
}