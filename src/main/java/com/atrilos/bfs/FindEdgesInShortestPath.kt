package com.atrilos.bfs

import java.util.*

/**
 * [3123](https://leetcode.com/problems/find-edges-in-shortest-paths/description/)
 */
class FindEdgesInShortestPath {
    fun findAnswer(n: Int, edges: Array<IntArray>): BooleanArray {
        val dist = LongArray(n) { Int.MAX_VALUE.toLong() }
        dist[0] = 0L

        val pq = PriorityQueue<Int>(compareBy { dist[it] })
        pq.add(0)

        val graph = mutableMapOf<Int, MutableList<IntArray>>()
        for ((i, edge) in edges.withIndex()) {
            val (u, v, w) = edge
            graph.getOrPut(u) { mutableListOf() }.add(intArrayOf(v, w, i))
            graph.getOrPut(v) { mutableListOf() }.add(intArrayOf(u, w, i))
        }

        while (!pq.isEmpty()) {
            val u = pq.remove()
            for ((v, w, _) in graph[u] ?: emptyList()) {
                val nextDist = dist[u] + w
                if (nextDist < dist[v]) {
                    dist[v] = nextDist
                    pq.add(v)
                }
            }
        }

        val ans = BooleanArray(edges.size)
        val q = LinkedList<Int>()
        val seen = BooleanArray(n)

        q.add(n - 1)
        seen[n - 1] = true

        while (!q.isEmpty()) {
            val u = q.remove()
            for ((v, w, i) in graph[u] ?: emptyList()) {
                if (dist[v] == dist[u] - w) {
                    ans[i] = true
                    if (!seen[v]) {
                        seen[v] = true
                        q.add(v)
                    }
                }
            }
        }

        return ans
    }
}