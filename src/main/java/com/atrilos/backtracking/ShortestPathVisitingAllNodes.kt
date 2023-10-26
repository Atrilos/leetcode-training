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

fun shortestPathLengthEasier(graph: Array<IntArray>): Int {
    if (graph.size == 1) {
        return 0
    }

    val n = graph.size
    val endingMask = (1 shl n) - 1
    val seen = Array(n) { BooleanArray(endingMask) }
    val queue = java.util.ArrayDeque<IntArray>()

    for (i in 0 until n) {
        queue.add(intArrayOf(i, 1 shl i))
        seen[i][1 shl i] = true
    }

    var steps = 0
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val currentPair = queue.removeFirst()
            val node = currentPair[0]
            val mask = currentPair[1]
            for (neighbor in graph[node]) {
                val nextMask = mask or (1 shl neighbor)
                if (nextMask == endingMask) {
                    return 1 + steps
                }
                if (!seen[neighbor][nextMask]) {
                    seen[neighbor][nextMask] = true
                    queue.addLast(intArrayOf(neighbor, nextMask))
                }
            }
        }
        steps++
    }
    return -1
}