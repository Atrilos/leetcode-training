package com.atrilos.graphs

/**
 * [2959](https://leetcode.com/problems/number-of-possible-sets-of-closing-branches/description/)
 */
class NumberOfPossibleSetsOfClosingBranches {
    private var res = 0

    fun numberOfSets(n: Int, maxDistance: Int, roads: Array<IntArray>): Int {
        val graph = Array(n) { IntArray(n) }

        for (road in roads) {
            val u = road[0]
            val v = road[1]
            val w = road[2]
            graph[u][v] = if (graph[u][v] == 0) w else minOf(graph[u][v], w)
            graph[v][u] = if (graph[v][u] == 0) w else minOf(graph[v][u], w)
        }

        solve(n, ArrayList(), graph, maxDistance)

        return res
    }

    private fun solve(
        n: Int,
        closedBranches: MutableList<Int>,
        graph: Array<IntArray>,
        maxDistance: Int
    ) {

        val cbSize = closedBranches.size
        val cb = closedBranches.toIntArray()
        if (helper(graph, cb, maxDistance)) {
            res++
        }
        var i = if (cbSize == 0) 0 else closedBranches[cbSize - 1] + 1
        while (i < n) {
            closedBranches.add(i)
            solve(n, closedBranches, graph, maxDistance)
            closedBranches.removeLast()
            i++
        }
    }

    private fun helper(graph: Array<IntArray>, closedBranches: IntArray, maxDistance: Int): Boolean {
        val hSet = closedBranches.toSet()

        val n = graph.size
        val graphClone = Array(n) { IntArray(n) }

        for (i in graph.indices) {
            if (hSet.contains(i)) continue
            for (j in graph[i].indices) {
                if (hSet.contains(j)) continue
                graphClone[i][j] = graph[i][j]
            }
        }

        for (i in graph.indices) {
            if (!hSet.contains(i)) {
                if (!isValid(graphClone, hSet, i, maxDistance)) {
                    return false
                }
            }
        }

        return true
    }

    private fun isValid(graph: Array<IntArray>, closedBranches: Set<Int>, source: Int, maxDistance: Int): Boolean {
        val n = graph.size
        val dist = IntArray(n) { Int.MAX_VALUE }
        val seen = BooleanArray(n)

        dist[source] = 0

        repeat (n) {
            var u = -1
            for (v in 0 until n) {
                if (seen[v]) continue
                if (dist[v] != Int.MAX_VALUE && (u == -1 || dist[u] > dist[v])) {
                    u = v
                }
            }
            if (u == -1) return@repeat
            seen[u] = true
            for (v in 0 until n) {
                if (!seen[v] && graph[u][v] != 0 && (dist[v] > dist[u] + graph[u][v])) {
                    dist[v] = dist[u] + graph[u][v]
                }
            }
        }
        for (i in 0 until n) {
            if (closedBranches.contains(i)) continue
            if (dist[i] > maxDistance) {
                return false
            }
        }

        return true
    }
}