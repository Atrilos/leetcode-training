package com.atrilos.heapPrioQueue

/**
 * [2940](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/description/)
 */
class LeftmostBuildingQueries {

    fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
        val n = queries.size
        val queue = mutableListOf<MutableList<IntArray>>()

        for (i in heights.indices) {
            queue.add(mutableListOf())
        }
        val result = IntArray(n) { -1 }
        for (i in queries.indices) {
            val (a, b) = queries[i]
            if (a < b && heights[a] < heights[b]) {
                result[i] = b
            } else if (a == b || (a > b && heights[a] > heights[b])) {
                result[i] = a
            } else {
                queue[maxOf(a, b)].add(intArrayOf(maxOf(heights[a], heights[b]), i))
            }
        }

        val pq = java.util.PriorityQueue<IntArray> { x, y -> x[0] - y[0] }
        for (i in heights.indices) {
            while (pq.isNotEmpty() && pq.peek()[0] < heights[i]) {
                result[pq.remove()[1]] = i
            }
            for (arr in queue[i]) {
                pq.add(arr)
            }
        }
        return result
    }
}