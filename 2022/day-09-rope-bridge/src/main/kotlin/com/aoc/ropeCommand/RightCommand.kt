package com.aoc.ropeCommand

import com.aoc.Rope
import com.aoc.RopeMover

class RightCommand(distance: Int = 0): RopeCommand(distance) {
  override fun moveRope(rope: Rope): Rope {
    return RopeMover.moveRight(rope)
  }
}