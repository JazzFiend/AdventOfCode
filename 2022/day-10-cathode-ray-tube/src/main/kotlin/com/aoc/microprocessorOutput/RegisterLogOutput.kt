package com.aoc.microprocessorOutput

class RegisterLogOutput: MicroprocessorOutput() {
  private val registerLog = ArrayList<Int>()

  override fun update(cycle:Int, register:Int) {
    registerLog.add(register)
  }

  override fun getOutputValue(): List<Int> {
    return registerLog
  }
}
