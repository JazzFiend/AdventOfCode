package com.aoc.microprocessorOutput

class SignalStrengthOutput(private val firstOutputCycle: Int, private val outputPeriod: Int): MicroprocessorOutput() {
  val signalOutputs = ArrayList<Int>()

  override fun update(cycle:Int, register:Int) {
    if(cycle == firstOutputCycle || isMultipleAfterFirst(cycle)) {
      signalOutputs.add(cycle * register)
    }
  }

  override fun getOutputValue(): List<Int> {
    return signalOutputs
  }

  private fun isMultipleAfterFirst(cycle: Int) =
    cycle > firstOutputCycle && ((cycle - firstOutputCycle) % outputPeriod) == 0
}