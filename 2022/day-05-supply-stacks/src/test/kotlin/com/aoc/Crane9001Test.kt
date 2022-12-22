package com.aoc

import com.aoc.TestUtils.Companion.assertStackCorrect
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class Crane9001Test {
  @Test
  fun noInstructions() {
    val crane = Crane9001()
    val stacks = listOf(Stack<Char>())
    assertStackCorrect(crane.moveCrates(stacks, ArrayList()), ArrayList())
  }

  @Test
  fun moveOneCrateToNewStack() {
    val crane = Crane9001()
    val stacksInitial = "[A] [V] [M]\n" +
                        "[L] [B] [R]\n" +
                        "[W] [Z] [F]\n" +
                        " 1   2   3"
    val stacksExpected = listOf("WLA", "ZB", "FRMV")
    val instructions = listOf("move 1 from 2 to 3")
    val stacks = StackCreator.create(stacksInitial)
    assertStackCorrect(crane.moveCrates(stacks, instructions), stacksExpected)
  }

  @Test
  fun moveManyCratesToNewStack() {
    val crane = Crane9001()
    val stacksInitial = "[A] [V] [M]\n" +
                        "[L] [B] [R]\n" +
                        "[W] [Z] [F]\n" +
                        " 1   2   3"
    val stacksExpected = listOf("", "ZBV", "FRMWLA")
    val instructions = listOf("move 3 from 1 to 3")
    val stacks = StackCreator.create(stacksInitial)
    assertStackCorrect(crane.moveCrates(stacks, instructions), stacksExpected)
  }

  @Test
  fun acceptanceTest() {
    val crane = Crane9001()
    val stacksInitial = "    [D]    \n" +
                        "[N] [C]    \n" +
                        "[Z] [M] [P]\n" +
                        " 1   2   3"
    val stacksExpected = listOf("M", "C", "PZND")
    val instructions = listOf("move 1 from 2 to 1", "move 3 from 1 to 3", "move 2 from 2 to 1", "move 1 from 1 to 2")
    val stacks = StackCreator.create(stacksInitial)
    assertStackCorrect(crane.moveCrates(stacks, instructions), stacksExpected)
  }

  @Test
  fun puzzleTwo() {
    val crane = Crane9001()
    val instructions = ArrayList<String>()
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine { instructions.add(it) }
    val stacksInitial = "[F]         [L]     [M]            \n" +
                        "[T]     [H] [V] [G] [V]            \n" +
                        "[N]     [T] [D] [R] [N]     [D]    \n" +
                        "[Z]     [B] [C] [P] [B] [R] [Z]    \n" +
                        "[M]     [J] [N] [M] [F] [M] [V] [H]\n" +
                        "[G] [J] [L] [J] [S] [C] [G] [M] [F]\n" +
                        "[H] [W] [V] [P] [W] [H] [H] [N] [N]\n" +
                        "[J] [V] [G] [B] [F] [G] [D] [H] [G]\n" +
                        " 1   2   3   4   5   6   7   8   9"
    val stacksExpected = listOf("N", "VRMGGGG", "DDWMBGTFHVNNVFPVCBNZRZJLHJBWNC", "HSM", "HMTGJFHP", "MJ", "L", "DFH", "V")

    val stacks = StackCreator.create(stacksInitial)
    assertStackCorrect(crane.moveCrates(stacks, instructions), stacksExpected)
  }
}