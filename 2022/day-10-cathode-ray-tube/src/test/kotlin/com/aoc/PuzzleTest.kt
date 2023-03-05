package com.aoc

import com.aoc.microprocessorOutput.SignalStrengthOutput
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class PuzzleTest {
  @Nested
  class SignalStrengthTests {
    @Test
    fun `Acceptance Test 1`() {
      val commands = listOf("noop", "addx 3", "addx -5")
      val relevantSignals = Microprocessor.runProgram(commands, SignalStrengthOutput(0, 1))
      assertEquals(listOf(1, 2, 3, 16, 20), relevantSignals)
    }

    @Test
    fun `Acceptance Test 2`() {
      val commands = ArrayList<String>()
      File("./src/test/kotlin/com/aoc/example2.txt").forEachLine { commands.add(it) }
      val relevantSignals = Microprocessor.runProgram(commands, SignalStrengthOutput(20, 40))
      assertEquals(listOf(420, 1140, 1800, 2940, 2880, 3960), relevantSignals)
      assertEquals(13140, relevantSignals.reduce { prev, cur -> prev + cur })
    }

    @Test
    fun `Puzzle 1`() {
      val commands = ArrayList<String>()
      File("./src/test/kotlin/com/aoc/input.txt").forEachLine { commands.add(it) }
      val relevantSignals = Microprocessor.runProgram(commands, SignalStrengthOutput(20, 40))
      assertEquals(13060, relevantSignals.reduce { prev, cur -> prev + cur })
    }
  }

  @Nested
  class CommunicationDeviceTests {
    @Test
    fun `Acceptance Test`() {
      val expected = ArrayList<String>()
      expected.add("##..##..##..##..##..##..##..##..##..##..")
      expected.add("###...###...###...###...###...###...###.")
      expected.add("####....####....####....####....####....")
      expected.add("#####.....#####.....#####.....#####.....")
      expected.add("######......######......######......####")
      expected.add("#######.......#######.......#######.....")

      val commands = ArrayList<String>()
      File("./src/test/kotlin/com/aoc/example2.txt").forEachLine { commands.add(it) }
      assertEquals(expected, CommunicationDevice.generateDisplay(commands))
    }

    @Test
    fun `Puzzle 2`() {
      val expected = ArrayList<String>()
      expected.add("####...##.#..#.###..#..#.#....###..####.")
      expected.add("........#.#..#.#..#.#..#.#....#..#....#.")
      expected.add("###.....#.#..#.###..#..#.#....#..#...#..")
      expected.add("........#.#..#.#..#.#..#.#....###...#...")
      expected.add(".....#..#.#..#.#..#.#..#.#....#.#..#....")
      expected.add("......##...##..###...##..####.#..#.####.")
      val commands = ArrayList<String>()
      File("./src/test/kotlin/com/aoc/input.txt").forEachLine { commands.add(it) }
      assertEquals(expected, CommunicationDevice.generateDisplay(commands))
    }
  }
}
