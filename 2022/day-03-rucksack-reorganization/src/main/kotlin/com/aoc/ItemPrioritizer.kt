package com.aoc

private const val LOWER_CASE_OFFSET = 96
private const val UPPER_CASE_OFFSET = 38

class ItemPrioritizer {
    companion object {
        fun prioritizeItem(errorItem: Char) : Int = if (errorItem.isLowerCase()) {
            errorItem.code - LOWER_CASE_OFFSET
        } else {
            errorItem.code - UPPER_CASE_OFFSET
        }
    }

}