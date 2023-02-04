package com.aoc.ropeCommand

import com.aoc.Rope

abstract class RopeCommand(private val distance: Int) {
  fun execute(rope: Rope): Rope {
    var r = rope
    IntRange(1, distance).forEach { _ ->
      r = moveRope(r)
    }
    return r
  }

  abstract fun moveRope(rope: Rope): Rope
}