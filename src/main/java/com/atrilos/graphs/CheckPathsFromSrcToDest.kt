package com.atrilos.graphs

/**
 * [1059](https://leetcode.com/problems/all-paths-from-source-lead-to-destination/)
 */
class CheckPathsFromSrcToDest {

    fun leadsToDestination(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        for ((from, to) in edges) {
            graph.getOrPut(from) { mutableSetOf() }.add(to)
        }
        return dfs(graph, source, destination, mutableMapOf())
    }

    private fun dfs(
        graph: MutableMap<Int, MutableSet<Int>>,
        current: Int,
        destination: Int,
        seen: MutableMap<Int, Boolean>
    ): Boolean {
        if (current in seen) {
            return seen[current]!!
        }
        if (current !in graph) {
            return current == destination
        }

        seen[current] = false
        if (!graph[current]!!.all { dfs(graph, it, destination, seen) }) {
            return false
        }
        seen[current] = true

        return true
    }
}