package com.atrilos.graphs

/**
 * [3108](https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/description/)
 */
class MinCostWalkInWeightGraph {
    fun minimumCost(n: Int, edges: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val uf = DSU(n)

        for (edge in edges) {
            uf.union(edge[0], edge[1], edge[2])
        }

        val result = IntArray(queries.size)

        for (i in queries.indices) {
            result[i] = uf.minimumCostOfWalk(queries[i][0], queries[i][1])
        }

        return result
    }

    class DSU(n: Int) {
        var parent: IntArray = IntArray(n) { it }
        private var rank: IntArray = IntArray(n)
        private var weights: IntArray = IntArray(n) { 0x1FFFF }

        fun find(x: Int): Int {
            if (x != parent[x]) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(x: Int, y: Int, z: Int) {
            val xx = find(x)
            val yy = find(y)
            if (rank[xx] < rank[yy]) {
                parent[xx] = yy
            }
            else {
                parent[yy] = xx
            }
            weights[yy] = weights[xx] and weights[yy] and z
            weights[xx] = weights[yy]
            if (rank[xx] == rank[yy]) {
                rank[xx]++
            }
        }

        fun minimumCostOfWalk(x: Int, y: Int): Int {
            if (x == y) {
                return 0
            }
            if (find(x) != find(y)) {
                return -1
            }
            return weights[find(x)]
        }
    }
}