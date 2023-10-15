package com.atrilos.dp2d

/**
 * [120](https://leetcode.com/problems/triangle/description/)
 */
class Triangle {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val dp = Array(triangle.size + 1) { IntArray(it + 1) }

        for (i in dp.lastIndex - 1 downTo 0) {
            for (j in dp[i].indices) {
                dp[i][j] = triangle[i][j] + minOf(dp[i + 1][j], dp[i + 1][j + 1])
            }
        }

        return dp[0][0]
    }
}