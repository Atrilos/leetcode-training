package com.atrilos.heapPrioQueue

/**
 * [502](https://leetcode.com/problems/ipo/description/)
 */
fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
    var currentCapital = w
    var count = k
    val indices = (0..capital.lastIndex).sortedWith(compareByDescending { capital[it] }).toMutableList()
    val pq = java.util.PriorityQueue<Int>(compareByDescending { it })

    while (count > 0) {
        while (indices.isNotEmpty() && currentCapital >= capital[indices.last()]) {
            pq.add(profits[indices.removeLast()])
        }
        if (pq.isNotEmpty()) {
            currentCapital += pq.remove()
            count--
        } else break
    }
    return currentCapital
}