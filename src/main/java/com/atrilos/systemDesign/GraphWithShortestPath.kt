package com.atrilos.systemDesign

/**
 * [2642](https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/)
 */
class GraphWithShortestPath(n: Int, edges: Array<IntArray>) {

    data class EdgeWithCost(val to: Int, val cost: Int)

    val edges = mutableMapOf<Int, MutableList<EdgeWithCost>>()

    init {
        for (i in 0..<n) {
            this.edges[i] = mutableListOf()
        }
        for ((from, to, cost) in edges) {
            this.edges[from]!!.add(EdgeWithCost(to, cost))
        }
    }

    fun addEdge(edge: IntArray) {
        val (from, to, cost) = edge
        edges[from]!!.add(EdgeWithCost(to, cost))
    }

    fun shortestPath(node1: Int, node2: Int): Int {
        val pq = java.util.PriorityQueue<EdgeWithCost>(compareBy { it.cost }).apply { add(EdgeWithCost(node1, 0)) }
        val distance = IntArray(edges.size) { Int.MAX_VALUE }
        distance[node1] = 0

        while (pq.isNotEmpty()) {
            val (to, cost) = pq.remove()
            if (to == node2) {
                return cost
            }
            if (cost > distance[to]) continue
            for ((nextTo, nextCost) in edges[to]!!) {
                if (cost + nextCost < distance[nextTo]) {
                    pq.add(EdgeWithCost(nextTo, cost + nextCost))
                    distance[nextTo] = cost + nextCost
                }
            }
        }

        return -1
    }

}