package com.atrilos.graphs

/**
 * [685](https://leetcode.com/problems/redundant-connection-ii/description/)
 */
class RedundantConnection2 {
    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        val indegree = IntArray(edges.size + 1) { -1 }
        var candidate1 = -1
        var candidate2 = -1
        for (i in edges.indices) {
            if (indegree[edges[i][1]] == -1) {
                indegree[edges[i][1]] = i
            } else {
                candidate1 = indegree[edges[i][1]]
                candidate2 = i
                break
            }
        }

        val parent = IntArray(edges.size + 1) { it }

        for (i in edges.indices) {
            if (i == candidate2) {
                continue
            }

            val parent1 = find(parent, edges[i][0])
            if (parent1 == edges[i][1]) {
                return if (candidate1 == -1) {
                    edges[i]
                } else {
                    edges[candidate1]
                }
            }
            parent[edges[i][1]] = parent1
        }

        return edges[candidate2]
    }

    fun find(parent: IntArray, x: Int): Int {
        if (parent[x] == x) {
            return x
        } else {
            parent[x] = find(parent, parent[x])
            return parent[x]
        }
    }
}