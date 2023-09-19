package com.atrilos.backtracking

import kotlin.math.abs

/**
 * [1584](https://leetcode.com/problems/min-cost-to-connect-all-points/description/)
 */
class MinCostToConnect {
    data class Edge(val id: Int, val dist: Int)

    fun minCostConnectPoints(points: Array<IntArray>): Int {
        var ans = 0
        val pq = java.util.PriorityQueue<Edge>(compareBy { it.dist })
        val mstPoints = mutableSetOf<Int>()

        pq.offer(Edge(0, 0))

        while (!pq.isEmpty()) {
            val (id, dist) = pq.poll()
            if (id in mstPoints) continue
            mstPoints.add(id)
            ans += dist

            for (i in points.indices) {
                if (i in mstPoints) continue

                pq.offer(Edge(i, getDist(points, id, i)))
            }
        }

        return ans
    }

    private fun getDist(points: Array<IntArray>, p: Int, q: Int): Int {
        return abs(points[p][0] - points[q][0]) + abs(points[p][1] - points[q][1])
    }
}