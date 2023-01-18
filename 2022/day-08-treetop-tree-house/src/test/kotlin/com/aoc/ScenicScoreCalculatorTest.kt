package com.aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class ScenicScoreCalculatorTest {
  @Test
  fun `No Trees`() {
    assertEquals(0, ScenicScoreCalculator.calculateMaxScore(listOf(listOf())))
  }

  @Test
  fun `One tree should have a score of zero`() {
    val trees = listOf(listOf(7))
    assertEquals(0, ScenicScoreCalculator.calculateMaxScore(trees))
  }

  @Test
  fun `Two trees of any height should have a score of zero`() {
    val trees = listOf(listOf(6, 7))
    assertEquals(0, ScenicScoreCalculator.calculateMaxScore(trees))
  }

  // **** I'm not calculating the scenic score right. It needs to be a product, not a sum.
  @Test
  fun `A tree in the middle of a 3x3 that can see one tree from each direction should have a score of 1`() {
    val trees = listOf(
      listOf(1, 2, 3),
      listOf(4, 5, 4),
      listOf(3, 2, 1)
    )
    assertEquals(1, ScenicScoreCalculator.calculateMaxScore(trees))
  }

  @Test
  fun `Acceptance Test`() {
    val trees = listOf(
      listOf(3, 0, 3, 7, 3),
      listOf(2, 5, 5, 1, 2),
      listOf(6, 5, 3, 3, 2),
      listOf(3, 3, 5, 4, 9),
      listOf(3, 5, 3, 9, 0)
    )
    assertEquals(8, ScenicScoreCalculator.calculateMaxScore(trees))
  }

  @Test
  fun `Puzzle 2`() {
    val trees = ArrayList<List<Int>>()
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine { line ->
      val charArray = line.toCharArray()
      val intArray = charArray.map { character -> character.code }
      trees.add(intArray.toList())
    }
    assertEquals(371200, ScenicScoreCalculator.calculateMaxScore(trees))
  }
}