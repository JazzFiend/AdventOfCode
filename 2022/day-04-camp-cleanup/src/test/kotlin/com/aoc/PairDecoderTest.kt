package com.aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PairDecoderTest {
    @Test
    fun allSingleDigits() {
        assertEquals(Pair(Assignment(1, 2), Assignment(3, 4)), PairDecoder.decodePair("1-2,3-4"))
    }

    @Test
    fun allMultiDigits() {
        assertEquals(Pair(Assignment(83, 599), Assignment(984, 9484)), PairDecoder.decodePair("83-599,984-9484"))
    }
}