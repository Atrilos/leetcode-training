package com.atrilos.graphs

fun minReorder(n: Int, connections: Array<IntArray>): Int {
    val g = Array(n + 1) { mutableListOf<Pair<Int, Boolean>>() }
    for ((a, b) in connections) {
        g[a].add(b to true) // this edge is provided
        g[b].add(a to false) // this edge is imagined
    }

    fun dfs(parent: Int, node: Int): Int {
        var res = 0
        for ((next, provided) in g[node]) {
            if (next == parent) continue
            if (provided) // we need to reverse it
                res++
            res += dfs(node, next)
        }
        return res
    }
    return dfs(-1, 0)
}