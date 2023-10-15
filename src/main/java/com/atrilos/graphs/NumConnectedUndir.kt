package com.atrilos.graphs

/**
 * [323](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)
 */
class NumConnectedUndir {

    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val visited = BooleanArray(n)
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        for ((from, to) in edges) {
            graph.getOrPut(from) { mutableSetOf() }.add(to)
            graph.getOrPut(to) { mutableSetOf() }.add(from)
        }
        var count = 0
        (0..<n).forEach {
            if (!visited[it]) {
                count++
                dfs(it, visited, graph)
            }
        }
        return count
    }

    private fun dfs(current: Int, visited: BooleanArray, graph: Map<Int, Set<Int>>) {
        if (visited[current]) return
        visited[current] = true
        graph[current]?.let { it.forEach { dfs(it, visited, graph) } }
    }
}