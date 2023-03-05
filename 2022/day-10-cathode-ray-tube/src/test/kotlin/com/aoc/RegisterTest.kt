package com.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.NullPointerException
import java.lang.RuntimeException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegisterTest {
  @Test
  fun `Default Constructor Test`() {
    val r = Register()
    assertEquals(1, r.value)
    assertFalse(r.inProgress)
  }

  @Test
  fun `Starting an add should flip the value of instructionStarted`() {
    val r = Register()
    r.startAdd(76)
    assertEquals(1, r.value)
    assertTrue(r.inProgress)
  }

  @Test
  fun `Add multiple numbers`() {
    val r = Register()
    r.startAdd(5)
    r.completeAdd()
    r.startAdd(9)
    r.completeAdd()
    assertEquals(15, r.value)
    assertFalse(r.inProgress)
  }

  @Test
  fun `Running completeAdd() first should throw an exception`() {
    val r = Register()
    assertThrows<NullPointerException> {
      r.completeAdd()
    }
  }

  @Test
  fun `Running completeAdd() first after an add should throw an exception`() {
    val r = Register()
    r.startAdd(7)
    r.completeAdd()
    assertThrows<NullPointerException> {
      r.completeAdd()
    }
  }

  @Test
  fun `Running startAdd() multiple times should throw an exception`() {
    val r = Register()
    r.startAdd(7)
    assertThrows<RuntimeException> {
      r.startAdd(45)
    }
  }
}