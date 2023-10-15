package com.atrilos.intervals

/**
 * [2251](https://leetcode.com/problems/number-of-flowers-in-full-bloom/description/)
 */
class FullBloomFlowers {
    val IN = -1
    val OBSERVE = 0
    val OUT = 1

    fun fullBloomFlowers(flowers: Array<IntArray>, people: IntArray): IntArray {
        val timeline = mutableListOf<Triple<Int, Int, Int>>() // time, state, peopleIndex

        for ((start, end) in flowers) {
            timeline.add(Triple(start, IN, -1))
            timeline.add(Triple(end, OUT, -1))
        }
        for ((i, time) in people.withIndex()) {
            timeline.add(Triple(time, OBSERVE, i))
        }

        timeline.sortWith(compareBy({ it.first }, { it.second }))

        val res = IntArray(people.size)
        var currentFlowers = 0
        for ((_, state, peopleIndex) in timeline) {
            when (state) {
                IN -> {
                    currentFlowers++
                }
                OUT -> {
                    currentFlowers--
                }
                OBSERVE -> {
                    res[peopleIndex] = currentFlowers
                }
            }
        }

        return res
    }
}