package com.atrilos.stack

import java.util.ArrayDeque

/**
 * https://leetcode.com/problems/daily-temperatures/
 * 739
 */
fun main() {
    println(dailyTemperatures(intArrayOf(30,60,90)).joinToString())
}
fun dailyTemperatures(temperatures: IntArray): IntArray {
    val deque = ArrayDeque<Pair<Int, Int>>()
    val res = IntArray(temperatures.size)

    for (i in temperatures.indices) {
        val todayTemp = temperatures[i]
        while (deque.isNotEmpty() && deque.peekFirst().first < todayTemp) {
            val (_, index) = deque.removeFirst()
            res[index] = i - index
        }
        deque.offerFirst(todayTemp to i)
    }

    return res
}