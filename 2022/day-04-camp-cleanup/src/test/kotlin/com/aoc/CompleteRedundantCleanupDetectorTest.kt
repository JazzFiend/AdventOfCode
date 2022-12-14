package com.aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class CompleteRedundantCleanupDetectorTest {
    private var rcd = CompleteRedundantCleanupDetector()

    @BeforeTest
    fun setup() {
        rcd = CompleteRedundantCleanupDetector()
    }

    @Test
    fun noPairsGiven() {
        assertEquals(0, rcd.countRedundantPairs(ArrayList()))
    }

    @Test
    fun noOverlap() {
        val pairs = listOf("1-2,3-4")
        assertEquals(0, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun partialOverlap() {
        val pairs = listOf("1-2,2-4")
        assertEquals(0, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun firstOverlapsSecond() {
        val pairs = listOf("1-5,2-4")
        assertEquals(1, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun secondOverlapsFirst() {
        val pairs = listOf("3-13,1-20")
        assertEquals(1, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun acceptanceTest() {
        val pairs = listOf("2-4,6-8", "2-3,4-5", "5-7,7-9", "2-8,3-7", "6-6,4-6", "2-6,4-8")
        assertEquals(2, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun puzzleOne() {
        val pairs = ArrayList<String>()
        File("./src/test/kotlin/com/aoc/input.txt").forEachLine { pairs.add(it) }
        assertEquals(602, rcd.countRedundantPairs(pairs))
    }
}