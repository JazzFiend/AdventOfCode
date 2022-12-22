package com.aoc

import java.util.*

class Crane9000 : Crane() {
  override fun executeInstruction(
    quantity: Int,
    crates: List<Stack<Char>>,
    destination: Int,
    source: Int
  ): List<Stack<Char>> {
    for (i in IntRange(1, quantity)) {
      crates[destination].add(crates[source].pop())
    }
    return crates
  }
}