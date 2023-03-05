package com.aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CrtDisplayTest {
  @Test
  fun `No commands should turn off the display completely`() {
    val expected = ArrayList<String>()
    expected.add("...")
    expected.add("...")
    expected.add("...")
    assertEquals(expected, CrtDisplay.generateDisplay(ArrayList(), 3, 3))
  }

  @Test
  fun `A register value of 0 should turn on the zeroth pixel`() {
    val expected = ArrayList<String>()
    expected.add("#..")
    expected.add("...")
    expected.add("...")
    val registerValues = listOf(1)
    assertEquals(expected, CrtDisplay.generateDisplay(registerValues, 3, 3))
  }

  @Test
  fun `The beam should register a value to the right of the target`() {
    val expected = ArrayList<String>()
    expected.add("##.")
    expected.add("...")
    expected.add("...")
    val registerValues = listOf(0, 0)
    assertEquals(expected, CrtDisplay.generateDisplay(registerValues, 3, 3))
  }

  @Test
  fun `The beam should register a value to the left of the target`() {
    val expected = ArrayList<String>()
    expected.add("###")
    expected.add("...")
    expected.add("...")
    val registerValues = listOf(1, 1, 1)
    assertEquals(expected, CrtDisplay.generateDisplay(registerValues, 3, 3))
  }

  @Test
  fun `The beam should not affect lines above or below`() {
    val expected = ArrayList<String>()
    expected.add("...")
    expected.add("###")
    expected.add("...")
    val registerValues = listOf(9, 9, 9, 1, 1, 1, 4, 4, 4)
    assertEquals(expected, CrtDisplay.generateDisplay(registerValues, 3, 3))
  }

  @Test
  fun `The beam should not overflow a line`() {
    val expected = ArrayList<String>()
    expected.add("...")
    expected.add(".##")
    expected.add("...")
    val registerValues = listOf(5, 4, 3, 2, 2, 2, 5, 5, 5)
    assertEquals(expected, CrtDisplay.generateDisplay(registerValues, 3, 3))
  }

  @Test
  fun `The height and width of the screen should be variable`() {
    val expected = ArrayList<String>()
    expected.add("#....")
    expected.add(".#...")
    expected.add("..#..")
    expected.add("...#.")
    expected.add("....#")
    val registerValues = listOf(
      0, 9, 9, 9, 9,
      9, 1, 9, 9, 9,
      9, 9, 1, 9, 9,
      9, 9, 9, 4, 9,
      9, 9, 9, 9, 4,
    )
    assertEquals(expected, CrtDisplay.generateDisplay(registerValues, 5, 5))
  }
}