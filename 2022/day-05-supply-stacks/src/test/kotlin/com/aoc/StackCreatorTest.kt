package com.aoc

import com.aoc.TestUtils.Companion.assertStackCorrect
import org.junit.jupiter.api.Test

class StackCreatorTest {
  @Test
  fun noStacks() {
    val stacks = StackCreator.create("")
    assertStackCorrect(stacks, ArrayList())
  }

  @Test
  fun oneEmptyStack() {
    val stacks = StackCreator.create(" 1 ")
    assertStackCorrect(stacks, listOf(""))
  }

  @Test
  fun oneStackOneBox() {
    val stacksText = "[C]\n" +
                     " 1 "
    val stacks = StackCreator.create(stacksText)
    assertStackCorrect(stacks, listOf("C"))
  }

  @Test
  fun oneStackManyBoxes() {
    val stacksText = "[S]\n" +
                     "[N]\n" +
                     "[B]\n" +
                     " 1 "
    val stacks = StackCreator.create(stacksText)
    assertStackCorrect(stacks, listOf("BNS"))
  }

  @Test
  fun manyStacksOneBoxPerStack() {
    val stacksText = "[N] [M] [C]\n" +
                     " 1   2   3"
    val stacks = StackCreator.create(stacksText)
    assertStackCorrect(stacks, listOf("N", "M", "C"))
  }

  @Test
  fun manyStacksManyBoxes() {
    val stacksText = "[A] [V] [M]\n" +
                     "[L] [B] [R]\n" +
                     "[W] [Z] [F]\n" +
                     " 1   2   3"
    val stacks = StackCreator.create(stacksText)
    assertStackCorrect(stacks, listOf("WLA", "ZBV", "FRM"))
  }

  @Test
  fun unequalStacks() {
    val stacksText = "[M]        \n" +
                     "[V]     [U]\n" +
                     "[C] [P] [A]\n" +
                     " 1   2   3"
    val stacks = StackCreator.create(stacksText)
    assertStackCorrect(stacks, listOf("CVM", "P", "AU"))
  }

  @Test
  fun emptyStackWithFullStacks() {
    val stacksText = "[E]        \n" +
                     "[H] [B]    \n" +
                     "[K] [L]    \n" +
                     " 1   2   3"
    val stacks = StackCreator.create(stacksText)
    assertStackCorrect(stacks, listOf("KHE", "LB", ""))
  }
}