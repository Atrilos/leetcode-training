package com.atrilos.graphs

/**
 * [2050](https://leetcode.com/problems/parallel-courses-iii/)
 */
class ParallelCourses3 {
    fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(n + 1)
        val maxStartTime = IntArray(n+1)
        for ((from, to) in relations) {
            graph.getOrPut(from) { mutableListOf() }.add(to)
            inDegree[to]++
        }
        val deque = java.util.ArrayDeque<Pair<Int, Int>>()
        for (i in 1..n) {
            if (inDegree[i] == 0) {
                deque.add(i to 0)
            }
        }
        var res = 0
        while (deque.isNotEmpty()) {
            val (node, currentTime) = deque.removeFirst()
            val endTime = currentTime + time[node - 1]
            res = maxOf(res, endTime)
            if (node in graph) {
                for (nextNode in graph[node]!!) {
                    inDegree[nextNode]--
                    maxStartTime[nextNode] = maxOf(maxStartTime[nextNode], endTime)
                    if (inDegree[nextNode] == 0) {
                        deque.addLast(nextNode to maxStartTime[nextNode])
                    }
                }
            }
        }

        return res
    }
}