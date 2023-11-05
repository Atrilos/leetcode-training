package com.atrilos.dp2d

/**
 * [256](https://leetcode.com/problems/paint-house/)
 */
class PaintHouse {
    fun minCost(costs: Array<IntArray>): Int {
        val n = costs.size
        val dp = Array(n) { IntArray(3) }
        dp[0] = costs[0]
        for (i in 1..costs.lastIndex) {
            dp[i][0] = minOf(dp[i - 1][1], dp[i - 1][2]) + costs[i][0]
            dp[i][1] = minOf(dp[i - 1][0], dp[i - 1][2]) + costs[i][1]
            dp[i][2] = minOf(dp[i - 1][0], dp[i - 1][1]) + costs[i][2]
        }
        return dp[n - 1].minOf { it }
    }
}

/**
 * [265](https://leetcode.com/problems/paint-house-ii/description/)
 */
class PaintHouse2 {
    fun minCostII(costs: Array<IntArray>): Int {
        val n = costs.size
        val m = costs[0].size
        val dp = Array(n) { IntArray(m) }
        dp[0] = costs[0]
        for (i in 1..costs.lastIndex) {
            for (j in 0..<m) {
                for (k in 0..<m) {
                    if (j == k) continue
                    if (dp[i][j] == 0) {
                        dp[i][j] = dp[i - 1][k] + costs[i][j]
                    } else {
                        dp[i][j] = minOf(dp[i][j], dp[i - 1][k] + costs[i][j])
                    }
                }
            }
        }
        return dp[n - 1].minOf { it }
    }
}