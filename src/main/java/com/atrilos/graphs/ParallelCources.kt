package com.atrilos.graphs

/**
 * [1136](https://leetcode.com/problems/parallel-courses/description/)
 */
class ParallelCources {
    fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
        var count = 0
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        var seen = 0
        val indegree = IntArray(n + 1)

        for ((from, to) in relations) {
            indegree[to]++
            graph.getOrPut(from) { mutableSetOf() }.add(to)
        }

        val queue = java.util.ArrayDeque<Int>()
        for (i in 1..n) {
            if (indegree[i] == 0) {
                queue.offerLast(i)
            }
        }

        while (queue.isNotEmpty()) {
            count++

            repeat(queue.size) {
                val next = queue.removeFirst()
                seen++
                graph[next]?.let {
                    it.forEach {
                        indegree[it]--
                        if (indegree[it] == 0) queue.offerLast(it)
                    }
                }
            }
        }
        return if (seen != n) -1 else count
    }
}