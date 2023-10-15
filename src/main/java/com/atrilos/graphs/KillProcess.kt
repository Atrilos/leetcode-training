package com.atrilos.graphs

/**
 * [582](https://leetcode.com/problems/kill-process/)
 */
class KillProcess {
    fun killProcess(pid: List<Int>, ppid: List<Int>, kill: Int): List<Int> {
        val graph = mutableMapOf<Int, MutableSet<Int>>()

        for ((i, id) in pid.withIndex()) {
            val parentId = ppid[i]
            graph.getOrPut(parentId) { mutableSetOf() }.add(id)
        }

        val deque = java.util.ArrayDeque<Int>().apply { add(kill) }
        val res = mutableListOf<Int>()

        while (deque.isNotEmpty()) {
            val id = deque.removeFirst()
            res += id
            graph[id]?.let {
                it.forEach {
                    deque.offerLast(it)
                }
            }
        }
        return res
    }
}