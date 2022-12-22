package com.aoc

import java.util.*

abstract class Crane {
  fun moveCrates(crates: List<Stack<Char>>, instructions: List<String>): List<Stack<Char>> {
    var cratesMutable = crates

    for (instruction in instructions) {
      val tokenizedInstruction = instruction.split(" ")
      val quantity = tokenizedInstruction[1].toInt()
      val source = tokenizedInstruction[3].toInt()
      val destination = tokenizedInstruction[5].toInt()

      cratesMutable = executeInstruction(quantity, cratesMutable, destination, source)
    }
    return cratesMutable
  }

  abstract fun executeInstruction(
    quantity: Int,
    crates: List<Stack<Char>>,
    destination: Int,
    source: Int
  ): List<Stack<Char>>
}