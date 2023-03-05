package com.aoc

import com.aoc.microprocessorOutput.MicroprocessorOutput

class Microprocessor {
  companion object {
    fun runProgram(commands: List<String>, output: MicroprocessorOutput): List<Int> {
      val register = Register()
      var cycleCount = 0
      var programCounter = -1

      while ((programCounter < commands.size - 1) || register.inProgress) {
        cycleCount++
        output.update(cycleCount, register.value)
        if (!register.inProgress) {
          programCounter++
          val commandTokenized = extractCommand(commands[programCounter])
          if (isAddCommand(commandTokenized["name"] ?: "")) {
            register.startAdd(commandTokenized["value"]?.toInt()!!)
          }
        } else {
          register.completeAdd()
        }
      }
      return output.getOutputValue()
    }

    private fun isAddCommand(commandName: String) =
      commandName == "addx"

    private fun extractCommand(command: String): Map<String, String> {
      val parsedCommand = HashMap<String, String>()
      val commandTokenized = command.split(" ")
      parsedCommand["name"] = commandTokenized[0]
      if (commandTokenized.size > 1) {
        parsedCommand["value"] = commandTokenized[1]
      }
      return parsedCommand
    }
  }
}