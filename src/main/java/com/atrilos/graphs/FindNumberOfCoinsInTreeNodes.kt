package com.atrilos.graphs

/**
 * [2973](https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes/description/)
 */
class FindNumberOfCoinsInTreeNodes {
    fun placedCoins(edges: Array<IntArray>, cost: IntArray): LongArray {
        val n = cost.size
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        val res = LongArray(n)

        for ((u, v) in edges) {
            adjList.getOrPut(u) { mutableListOf() }.add(v)
            adjList.getOrPut(v) { mutableListOf() }.add(u)
        }

        dfsSubTrees(adjList, res, 0, BooleanArray(n), cost)

        return res
    }

    private fun dfsSubTrees(
        adjList: MutableMap<Int, MutableList<Int>>,
        res: LongArray,
        u: Int,
        seen: BooleanArray,
        cost: IntArray
    ): NodeStats {
        seen[u] = true

        val nodeStats = NodeStats().add(cost[u])
        for (v in adjList[u] ?: emptyList()) {
            if (!seen[v]) {
                nodeStats.merge(dfsSubTrees(adjList, res, v, seen, cost))
            }
        }

        val answer = nodeStats.maxProduct()
        res[u] = answer.second

        return answer.first
    }

    data class NodeStats(
        val posItems: MutableList<Int> = mutableListOf(),
        val negItems: MutableList<Int> = mutableListOf(),
        var size: Int = 0
    ) {
        fun add(cost: Int): NodeStats {
            size++
            posItems += cost
            negItems += cost

            posItems.sortDescending()
            negItems.sort()

            while (posItems.size > 3) {
                posItems.removeLast()
            }
            while (negItems.size > 2) {
                negItems.removeLast()
            }

            return this
        }

        fun merge(other: NodeStats): NodeStats {
            this.size += other.size
            this.posItems.addAll(other.posItems)
            this.negItems.addAll(other.negItems)

            posItems.sortDescending()
            negItems.sort()

            while (posItems.size > 3) {
                posItems.removeLast()
            }
            while (negItems.size > 2) {
                negItems.removeLast()
            }

            return this
        }

        fun maxProduct(): Pair<NodeStats, Long> {
            if (size < 3) {
                return this to 1L
            }
            return this to maxOf(
                posItems[0].toLong() * posItems[1] * posItems[2],
                posItems[0].toLong() * negItems[0] * negItems[1],
                0L)
        }
    }
}