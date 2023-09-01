package com.atrilos.intervals

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * 435
 */

fun main() {
    println(eraseOverlapIntervals(arrayOf(
            intArrayOf(-52,31), intArrayOf(-73,-26), intArrayOf(82,97), intArrayOf(-65,-11), intArrayOf(-62,-49), intArrayOf(95,99), intArrayOf(58,95), intArrayOf(-31,49), intArrayOf(66,98), intArrayOf(-63,2), intArrayOf(30,47), intArrayOf(-40,-26)
    )))
}

fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    intervals.sortWith(compareBy { it[0] })
    var end = intervals[0][1]
    var toRemove = 0

    for (i in 1..intervals.lastIndex) {
        val currInterval = intervals[i]
        end = if (currInterval[0] >= end) {
            currInterval[1]
        } else {
            toRemove++
            minOf(end, currInterval[1])
        }
    }

    return toRemove
}