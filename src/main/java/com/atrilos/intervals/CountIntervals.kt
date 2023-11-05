package com.atrilos.intervals

import java.util.*

/**
 * [2276](https://leetcode.com/problems/count-integers-in-intervals/)
 */
class CountIntervals {
    private val map = TreeMap<Int, Int>()

    private var count = 0

    fun add(left: Int, right: Int) {
        var start = left
        var end = right

        while (true) {

            val lastEntry = map.floorEntry(end) ?: break

            if (lastEntry.value < start) {
                break
            }

            delete(lastEntry)

            start = minOf(start, lastEntry.key)
            end = maxOf(end, lastEntry.value)
        }

        insert(start, end)
    }

    private fun delete(mapEntry: Map.Entry<Int, Int>) {
        count -= mapEntry.value - mapEntry.key + 1
        map.remove(mapEntry.key)
    }

    private fun insert(key: Int, value: Int) {
        map[key] = value
        count += value - key + 1
    }

    fun count(): Int = count

}