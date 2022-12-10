package com.aoc

import com.aoc.ItemPrioritizer.Companion.prioritizeItem

class BadgeFinder {
    companion object {
        fun scoreBadges(sacks : List<String>) : Int {
            var total = 0
            for (i in 0 until (sacks.size / 3)) {
                total += findBadge(sacks, i * 3)
            }
            return total
        }

        private fun findBadge(sacks: List<String>, offset: Int): Int {
            for (item in sacks[offset].toCharArray()) {
                if (sacks[offset + 1].contains(item) && sacks[offset + 2].contains(item)) {
                    return prioritizeItem(item)
                }
            }
            throw Error("No badge found")
        }
    }
}