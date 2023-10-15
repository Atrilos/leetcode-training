package com.atrilos.dp1d

/**
 * [221](https://leetcode.com/problems/maximal-square/description/)
 */
class MaximalSquare {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val n = matrix.size
        val m = matrix[0].size
        val dp = Array(n + 1) { IntArray(m + 1) }
        var maxSide = 0

        for (i in 1..n) {
            for (j in 1..m) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = minOf(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1
                    maxSide = maxOf(maxSide, dp[i][j])
                }
            }
        }

        return maxSide * maxSide
    }
}