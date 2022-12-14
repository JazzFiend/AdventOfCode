package com.aoc

class CompleteRedundantCleanupDetector : RedundantCleanupDetector() {
    override fun isRedundant(first: Assignment, second: Assignment) : Boolean {
       return (first.start <= second.start && first.end >= second.end)
    }
}