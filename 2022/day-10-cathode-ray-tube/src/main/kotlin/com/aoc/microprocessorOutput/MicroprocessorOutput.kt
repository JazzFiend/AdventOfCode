package com.aoc.microprocessorOutput

abstract class MicroprocessorOutput {
  abstract fun update(cycle:Int, register:Int)
  abstract fun getOutputValue(): List<Int>
}