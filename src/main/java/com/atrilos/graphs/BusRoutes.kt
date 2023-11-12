package com.atrilos.graphs

/**
 * [815](https://leetcode.com/problems/bus-routes/)
 */
class BusRoutes {
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        val nodesToRoutes = mutableMapOf<Int, MutableList<Int>>() // routes with common stop
        for (i in routes.indices) {
            for (j in routes[i].indices) {
                nodesToRoutes.getOrPut(routes[i][j]) { mutableListOf() }.add(i)
            }
        }

        val seenNodes = mutableSetOf<Int>()
        val seenRoutes = mutableSetOf<Int>()
        val q = java.util.ArrayDeque<Pair<Int, Int>>()
        q += source to 0

        while (q.isNotEmpty()) {
            val (node, dist) = q.removeFirst()

            if (node == target) return dist
            if (node in seenNodes) continue

            seenNodes += node
            for (route in nodesToRoutes[node]!!) {
                if (route in seenRoutes) continue

                for (s in routes[route]) {
                    if (s in seenNodes) continue
                    q += s to dist + 1
                }

                seenRoutes += route
            }
        }

        return -1
    }
}