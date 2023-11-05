package com.atrilos.dp1d

/**
 * [276](https://leetcode.com/problems/paint-fence/description/)
 */
class PaintFence {
    fun numWays(n: Int, k: Int): Int {
        val dp = IntArray(n)
        for (i in 0..<n) {
            when (i) {
                0 -> {
                    dp[i] = k
                }

                1 -> {
                    dp[i] = k * k
                }

                else -> {
                    dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2])
                }
            }
        }
        return dp[n - 1]
    }
}