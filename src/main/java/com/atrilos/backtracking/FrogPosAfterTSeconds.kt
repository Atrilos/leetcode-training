package com.atrilos.backtracking

/**
 * [1377](https://leetcode.com/problems/frog-position-after-t-seconds/description/)
 */
class FrogPosAfterTSeconds {
    fun frogPosition(n: Int, edges: Array<IntArray>, t: Int, target: Int): Double {
        val g = mutableMapOf<Int, MutableList<Int>>()

        for ((from, to) in edges) {
            g.getOrPut(from) { mutableListOf() }.add(to)
            g.getOrPut(to) { mutableListOf() }.add(from)
        }

        val dp = Array(t + 1) { DoubleArray(n + 1) }
        dp[0][1] = 1.0

        fun dfs(currentTime: Int, currentPos: Int, parent: Int) {
            if (currentTime == t) {
                return
            }
            val filteredNeighbours = (g[currentPos] ?: emptyList()).filter { it != parent }

            if (filteredNeighbours.isEmpty()) {
                dp[currentTime + 1][currentPos] += dp[currentTime][currentPos]
                dfs(currentTime + 1, currentPos, parent)
                return
            }

            for (nei in filteredNeighbours) {
                dp[currentTime + 1][nei] += dp[currentTime][currentPos] / filteredNeighbours.size
                dfs(currentTime + 1, nei, currentPos)
            }
        }

        dfs(0, 1, 0)

        return dp[t][target]
    }
}