package com.aoc

import com.aoc.microprocessorOutput.RegisterLogOutput

class CommunicationDevice {
  companion object {
    fun generateDisplay(commands: List<String>): List<String> {
      val registerLog = Microprocessor.runProgram(commands, RegisterLogOutput())
      return CrtDisplay.generateDisplay(registerLog, 6, 40)
    }
  }
}