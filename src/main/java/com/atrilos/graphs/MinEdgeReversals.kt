package com.atrilos.graphs

/**
 * [2858](https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/description/)
 */
class MinEdgeReversals {
    fun minEdgeReversals(n: Int, edges: Array<IntArray>): IntArray {
        val g = mutableMapOf<Int, MutableList<WeightedNode>>()

        for ((from, to) in edges) {
            g.getOrPut(from) { mutableListOf() }.add(WeightedNode(to, 0))
            g.getOrPut(to) { mutableListOf() }.add(WeightedNode(from, 1))
        }

        val res = IntArray(n) { -1 }
        res[0] = dfsFull(g, -1, 0)
        dfsPartial(g, res, 0)

        return res
    }

    private fun dfsPartial(
        g: MutableMap<Int, MutableList<WeightedNode>>,
        res: IntArray,
        u: Int
    ) {
        for ((v, weight) in g[u] ?: emptyList()) {
            if (res[v] != -1) continue
            if (weight == 0) {
                res[v] = res[u] + 1
            } else {
                res[v] = res[u] - 1
            }
            dfsPartial(g, res, v)
        }
    }

    private fun dfsFull(
        g: MutableMap<Int, MutableList<WeightedNode>>,
        par: Int,
        u: Int
    ): Int {
        var res = 0

        for ((v, weight) in g[u] ?: emptyList()) {
            if (v == par) continue
            res += dfsFull(g, u, v) + weight
        }

        return res
    }

    data class WeightedNode(val node: Int, val weight: Int)
}