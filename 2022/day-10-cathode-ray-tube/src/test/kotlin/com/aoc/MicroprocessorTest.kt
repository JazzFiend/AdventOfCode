package com.aoc

import com.aoc.microprocessorOutput.RegisterLogOutput
import com.aoc.microprocessorOutput.SignalStrengthOutput
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MicroprocessorTest {
  @Nested
  class SignalStrength {
    @Test
    fun `No commands should give no signal strengths`() {
      assertTrue(Microprocessor.runProgram(ArrayList(), SignalStrengthOutput(0, 1)).isEmpty())
    }

    @Test
    fun `One noop command should leave the register unchanged`() {
      val commands = listOf("noop")
      val signalStrengths = Microprocessor.runProgram(commands, SignalStrengthOutput(0, 1))
      assertEquals(listOf(1), signalStrengths)
    }

    @Test
    fun `Use two noops to show that the signal strength is the register value times the program counter`() {
      val commands = listOf("noop", "noop")
      val signalStrengths = Microprocessor.runProgram(commands, SignalStrengthOutput(0, 1))
      assertEquals(listOf(1, 2), signalStrengths)
    }

    @Test
    fun `An add command should add to the register, and take effect after two clock ticks`() {
      val commands = listOf("addx 8", "noop")
      val signalStrengths = Microprocessor.runProgram(commands, SignalStrengthOutput(0, 1))
      assertEquals(listOf(1, 2, 3 * 9), signalStrengths)
    }

    @Test
    fun `Start outputting after the first clock tick`() {
      val commands = listOf("noop", "noop", "noop")
      val signalStrengths = Microprocessor.runProgram(commands, SignalStrengthOutput(2, 1))
      assertEquals(listOf(2, 3), signalStrengths)
    }
  }

  @Nested
  class RegisterValues {
    @Test
    fun `No instructions given`() {
      assertEquals(ArrayList(), Microprocessor.runProgram(ArrayList(), RegisterLogOutput()))
    }

    @Test
    fun `One Noop Instruction`() {
      val commands = listOf("noop")
      assertEquals(listOf(1), Microprocessor.runProgram(commands, RegisterLogOutput()))
    }

    @Test
    fun `One Add Instruction`() {
      val commands = listOf("addx 9")
      assertEquals(listOf(1, 1), Microprocessor.runProgram(commands, RegisterLogOutput()))
    }

    @Test
    fun `One Add, one Noop`() {
      val commands = listOf("addx 9", "noop")
      assertEquals(listOf(1, 1, 10), Microprocessor.runProgram(commands, RegisterLogOutput()))
    }

    @Test
    fun `End on an add`() {
      val commands = listOf("addx 5", "noop", "addx 3")
      assertEquals(listOf(1, 1, 6, 6, 6), Microprocessor.runProgram(commands, RegisterLogOutput()))
    }
  }
}
