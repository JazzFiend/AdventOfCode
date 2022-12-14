package com.aoc

abstract class RedundantCleanupDetector {
    fun countRedundantPairs(pairs : List<String>) : Int {
        var redundantCount = 0
        for (pairString in pairs) {
            val pair = PairDecoder.decodePair(pairString)
            if(this.isRedundant(pair.first, pair.second) ||
                this.isRedundant(pair.second, pair.first)) {
                redundantCount++
            }
        }
        return redundantCount
    }

    abstract fun isRedundant(first: Assignment, second: Assignment) : Boolean
}