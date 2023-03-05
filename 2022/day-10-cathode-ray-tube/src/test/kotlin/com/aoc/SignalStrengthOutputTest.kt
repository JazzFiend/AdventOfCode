package com.aoc

import com.aoc.microprocessorOutput.SignalStrengthOutput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SignalStrengthOutputTest {
  @Test
  fun `Signals should be empty upon creation`() {
    val output = SignalStrengthOutput(1, 8)
    assertEquals(ArrayList(), output.signalOutputs)
  }

  @Test
  fun `A successful update should compute the signal value correctly`() {
    val output = SignalStrengthOutput(6, 10)
    output.update(16, 5)
    assertEquals(listOf(5 * 16), output.signalOutputs)
  }

  @Test
  fun `Check the boundary case for a successful update`() {
    val output = SignalStrengthOutput(1, 1)
    output.update(1, 99)
    assertEquals(listOf(99), output.signalOutputs)
  }

  @Test
  fun `Update every cycle`() {
    val output = SignalStrengthOutput(0, 1)
    output.update(1, 99)
    output.update(2, 100)
    assertEquals(listOf(99, 200), output.signalOutputs)
  }

  @Test
  fun `Update every cycle after the first official one`() {
    val output = SignalStrengthOutput(2, 1)
    output.update(1, 1)
    output.update(2, 10)
    output.update(3, 100)
    assertEquals(listOf(20, 300), output.signalOutputs)
  }

  @Test
  fun `We should not update if the program counter hasn't exceeded the first output`() {
    val output = SignalStrengthOutput(5, 45)
    output.update(2, 99)
    assertEquals(ArrayList(), output.signalOutputs)
  }

  @Test
  fun `We should not update if the program counter isn't a multiple of the output period`() {
    val output = SignalStrengthOutput(3, 10)
    output.update(15, 88)
    assertEquals(ArrayList(), output.signalOutputs)
  }
}