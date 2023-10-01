package com.atrilos.intervals

/**
 * [1272](https://leetcode.com/problems/remove-interval/description/)
 */
fun removeInterval(intervals: Array<IntArray>, toBeRemoved: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    var i = 0

    // If there are no overlaps, add the interval to the list as is.
    while (i < intervals.size && intervals[i][1] < toBeRemoved[0]) {
        res += intervals[i++].toList()
    }

    while (i < intervals.size && intervals[i][0] < toBeRemoved[1]) {
        // Is there a left interval we need to keep?
        if (intervals[i][0] < toBeRemoved[0]) {
            res += listOf(intervals[i][0], toBeRemoved[0])
        }
        // Is there a right interval we need to keep?
        if (intervals[i][1] > toBeRemoved[1]) {
            res += listOf(toBeRemoved[1], intervals[i][1])
        }
        i++
    }

    // Add remaining intervals
    while (i < intervals.size) {
        res += intervals[i++].toList()
    }

    return res
}