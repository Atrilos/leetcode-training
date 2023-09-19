package com.atrilos.backtracking

import kotlin.math.abs

/**
 * [1631](https://leetcode.com/problems/path-with-minimum-effort/description/)
 */
fun minimumEffortPath(heights: Array<IntArray>): Int {
    data class Edge(val id: Pair<Int, Int>, val diff: Int)
    val dirs = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    //A*
    val pq = java.util.PriorityQueue<Edge>(compareBy { it.diff })
    val visited = mutableSetOf<Pair<Int, Int>>()
    var res = 0

    pq.offer(Edge(0 to 0, 0))

    while (pq.isNotEmpty()) {
        val (id, diff) = pq.poll()
        if (id in visited) continue
        visited.add(id)
        res = maxOf(res, diff)
        if (id == heights.lastIndex to heights[0].lastIndex) return res

        for (dir in dirs) {
            val newI = id.first + dir.first
            val newJ = id.second + dir.second
            if (newI !in 0..heights.lastIndex || newJ !in 0..heights[0].lastIndex)
                continue
            pq.offer(Edge(newI to newJ, getDiff(heights, id, newI to newJ)))
        }
    }

    return res
}

fun getDiff(heights: Array<IntArray>, oldCoord: Pair<Int, Int>, newCoord: Pair<Int, Int>): Int
        = abs(heights[newCoord.first][newCoord.second] - heights[oldCoord.first][oldCoord.second])