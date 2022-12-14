package com.aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.BeforeTest

class PartialRedundantCleanupDetectorTest {
    private var rcd = PartialRedundantCleanupDetector()

    @BeforeTest
    fun setup() {
        rcd = PartialRedundantCleanupDetector()
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
    fun noOverlapFirstIsLarger() {
        val pairs = listOf("44-55,3-4")
        assertEquals(0, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun partialOverlapAtTop() {
        val pairs = listOf("1-2,2-4")
        assertEquals(1, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun partialOverlapAtBottom() {
        val pairs = listOf("4-9,2-4")
        assertEquals(1, rcd.countRedundantPairs(pairs))
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
        assertEquals(4, rcd.countRedundantPairs(pairs))
    }

    @Test
    fun puzzleTwo() {
        val pairs = ArrayList<String>()
        File("./src/test/kotlin/com/aoc/input.txt").forEachLine { pairs.add(it) }
        assertEquals(891, rcd.countRedundantPairs(pairs))
    }
}