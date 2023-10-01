package com.atrilos.graphs

/**
 * [399](https://leetcode.com/problems/evaluate-division/description/)
 */
class EvaluateDiv {
    class Node(val label: String, val map: MutableMap<Node, Double> = mutableMapOf())

    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val graph = generateGraph(equations, values)

        return queries.map { q ->
            if (graph[q[0]] == null || graph[q[1]] == null) -1.0 else compute(
                graph[q[0]]!!,
                graph[q[1]]!!,
                mutableSetOf()
            )
        }.toDoubleArray()
    }

    private fun generateGraph(
        equations: List<List<String>>,
        values: DoubleArray
    ): Map<String, Node> {
        val map: MutableMap<String, Node> = mutableMapOf()

        for ((i, e) in equations.withIndex()) {
            val x = map[e[0]] ?: Node(e[0]).also { map[it.label] = it }
            val y = map[e[1]] ?: Node(e[1]).also { map[it.label] = it }
            x.map[y] = values[i]
            y.map[x] = 1 / values[i]
        }

        return map
    }

    private fun compute(x: Node, y: Node, visited: MutableSet<Node>): Double {
        if (x == y) return 1.0

        if (visited.contains(x)) return -1.0

        visited.add(x)

        for (e in x.map) {
            val res = compute(e.key, y, visited)
            if (res != -1.0) return e.value * res
        }

        return -1.0
    }
}