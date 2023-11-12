package com.atrilos.heapPrioQueue

/**
 * [1921](https://leetcode.com/problems/eliminate-maximum-number-of-monsters/description/)
 */
class EliminateMaxMonsters {
    fun eliminateMaximum(dist: IntArray, speed: IntArray): Int {
        val pq = java.util.PriorityQueue<Int>()
        for (i in dist.indices) {
            pq.add(1 + ((dist[i] - 1) / speed[i]))
        }
        var currentStep = 0
        var res = 0

        while (pq.isNotEmpty()) {
            val steps = pq.remove()
            if (currentStep == steps) break
            res++
            currentStep++
        }
        return res
    }
}