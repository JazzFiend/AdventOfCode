package com.aoc

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class VisibleTreeCounterTest {
  @Test
  fun `No trees`() {
    assertEquals(0, VisibleTreeCounter.countVisibleTrees(listOf(listOf())))
  }

  @Test
  fun `One tree is always visible`() {
    val trees = listOf(listOf(4))
    assertEquals(1, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Test
  fun `Two trees arranged horizontally - Both are visible`() {
    val trees = listOf(listOf(8, 4))
    assertEquals(2, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Test
  fun `Two trees arranged vertically - Both are visible`() {
    val trees = listOf(
      listOf(9),
      listOf(3)
    )
    assertEquals(2, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Test
  fun `Small square of trees - All are visible`() {
    val trees = listOf(
      listOf(7, 8),
      listOf(9, 7)
    )
    assertEquals(4, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Test
  fun `One tall tree in the middle of a 3x3 should be visible`() {
    val trees = listOf(
      listOf(7, 6, 8),
      listOf(3, 9, 5),
      listOf(7, 8, 9),
    )
    assertEquals(9, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Test
  fun `One short tree in the middle of a 3x3 should not be visible`() {
    val trees = listOf(
      listOf(1, 2, 3),
      listOf(4, 0, 5),
      listOf(6, 7, 8),
    )
    assertEquals(8, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Nested
  class `Visible From Each Direction` {
    @Test
    fun left() {
      val trees = listOf(
        listOf(1, 2, 3),
        listOf(0, 1, 5),
        listOf(6, 7, 8),
      )
      assertEquals(9, VisibleTreeCounter.countVisibleTrees(trees))
    }

    @Test
    fun top() {
      val trees = listOf(
        listOf(1, 1, 3),
        listOf(4, 2, 5),
        listOf(6, 7, 8),
      )
      assertEquals(9, VisibleTreeCounter.countVisibleTrees(trees))
    }

    @Test
    fun right() {
      val trees = listOf(
        listOf(1, 7, 3),
        listOf(5, 3, 2),
        listOf(6, 7, 8),
      )
      assertEquals(9, VisibleTreeCounter.countVisibleTrees(trees))
    }

    @Test
    fun bottom() {
      val trees = listOf(
        listOf(1, 8, 3),
        listOf(5, 4, 5),
        listOf(6, 3, 8),
      )
      assertEquals(9, VisibleTreeCounter.countVisibleTrees(trees))
    }
  }

  @Test
  fun `A tree blocked by trees of equal size should not be visible`() {
    val trees = listOf(
      listOf(5, 5, 5),
      listOf(5, 5, 5),
      listOf(5, 5, 5),
    )
    assertEquals(8, VisibleTreeCounter.countVisibleTrees(trees))
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
    assertEquals(21, VisibleTreeCounter.countVisibleTrees(trees))
  }

  @Test
  fun `Puzzle 1`() {
    val trees = ArrayList<List<Int>>()
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine {line ->
      val charArray = line.toCharArray()
      val intArray = charArray.map { character -> character.code }
      trees.add(intArray.toList())
    }
    assertEquals(1705, VisibleTreeCounter.countVisibleTrees(trees))
  }
}