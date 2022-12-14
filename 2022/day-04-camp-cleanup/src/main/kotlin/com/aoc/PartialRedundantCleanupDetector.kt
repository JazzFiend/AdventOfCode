package com.aoc

class PartialRedundantCleanupDetector : RedundantCleanupDetector() {
    override fun isRedundant(first: Assignment, second: Assignment): Boolean {
        return ((second.start >= first.start && second.start <= first.end) ||
            second.end >= first.start && second.end <= first.end)
    }
}