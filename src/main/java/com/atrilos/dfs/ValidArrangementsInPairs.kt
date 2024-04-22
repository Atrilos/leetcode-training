package com.atrilos.dfs

/**
 * [2097](https://leetcode.com/problems/valid-arrangement-of-pairs/description/)
 */
class ValidArrangementsInPairs {
    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        val inDeg = mutableMapOf<Int, Int>()
        val graph = mutableMapOf<Int, java.util.ArrayDeque<Int>>()
        var start = pairs[0][0]

        for ((u, v) in pairs) {
            graph.getOrPut(u) { java.util.ArrayDeque() }.offerLast(v)
            inDeg.merge(u, 1, Int::plus)
            inDeg.merge(v, -1, Int::plus)
        }

        for ((u, _) in pairs) {
            if (inDeg[u] == 1) {
                start = u
                break
            }
        }

        val path = mutableListOf<IntArray>()
        topPath(start, graph, path)

        return path.map { intArrayOf(it[0], it[1]) }.reversed().toTypedArray()
    }

    private fun topPath(s: Int, graph: MutableMap<Int, java.util.ArrayDeque<Int>>, path: MutableList<IntArray>) {
        val deque = graph.getOrDefault(s, java.util.ArrayDeque())

        while (!deque.isEmpty()) {
            val v = deque.removeLast()
            topPath(v, graph, path)
            path.add(intArrayOf(s, v))
        }
    }
}