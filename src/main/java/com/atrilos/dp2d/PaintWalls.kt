package com.atrilos.dp2d

/**
 * [2742](https://leetcode.com/problems/painting-the-walls/description/)
 */
class PaintWalls {

    fun paintWalls(cost: IntArray, time: IntArray): Int {
        val n = cost.size
        val dp = Array(n) { IntArray(n + 1) }

        fun dfs(i: Int, remain: Int): Int {
            if (remain <= 0) {
                return 0
            }
            if (i == n) {
                return 2e9.toInt()
            }
            if (dp[i][remain] != 0) {
                return dp[i][remain]
            }
            val take = cost[i] + dfs(i + 1, remain - 1 - time[i])
            val donTake = dfs(i + 1, remain)
            dp[i][remain] = minOf(take, donTake)

            return dp[i][remain]
        }

        return dfs(0, n)
    }

}