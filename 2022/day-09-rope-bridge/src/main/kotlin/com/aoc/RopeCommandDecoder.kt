package com.aoc

import com.aoc.ropeCommand.*

class RopeCommandDecoder {
  companion object {
    fun generateCommandList(commands: List<String>): List<RopeCommand> {
      val convertedCommands = ArrayList<RopeCommand>()
      commands.forEach {
        val arguments = it.split(" ")
        val direction = arguments[0]
        val distance = arguments[1].toInt()
        when (direction) {
          "R" -> convertedCommands.add(RightCommand(distance))
          "U" -> convertedCommands.add(UpCommand(distance))
          "L" -> convertedCommands.add(LeftCommand(distance))
          "D" -> convertedCommands.add(DownCommand(distance))
          else -> throw Exception("Bad instruction seen")
        }
      }
      return convertedCommands
    }
  }
}