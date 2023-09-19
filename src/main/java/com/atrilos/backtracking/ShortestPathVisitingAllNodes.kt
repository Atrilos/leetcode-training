package com.atrilos.backtracking

/**
 * [847](https://leetcode.com/problems/shortest-path-visiting-all-nodes/)
 */
fun shortestPathLength(graph: Array<IntArray>): Int {
    val n = graph.size
    // Bit mask with each node visited. 1 = true as in bitmap
    val mask = (1 shl n) - 1
    val queue = java.util.ArrayDeque<IntArray>()
    val visited = mutableSetOf<Int>()

    for (i in 0 until n) {
        queue.offerFirst(intArrayOf(1 shl i, i, 0))  // mask, node, dist
        visited += (1 shl i shl 12) + i
    }

    while (queue.isNotEmpty()) {
        val (visitedBitState, nodeId, distance) = queue.removeLast()

        if (visitedBitState == mask) {
            return distance
        }

        for (neighbour in graph[nodeId]) {
            val newMask = visitedBitState or (1 shl neighbour)
            val hashValue = (newMask shl 12) + neighbour  // Hash with next node and bit mask after visiting it

            if (hashValue !in visited) {
                visited += hashValue
                queue.offerFirst(intArrayOf(newMask, neighbour, distance + 1))
            }
        }
    }

    return -1
}