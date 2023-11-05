package com.atrilos.dp1d

/**
 * [651](https://leetcode.com/problems/4-keys-keyboard/description/)
 */
class MaxA {
    fun maxA(n: Int): Int {
        val dp = IntArray(n + 1)
        for (i in 0..n) {
            if (i <= 5) {
                dp[i] = i
            } else {
                dp[i] = maxOf(dp[i - 1] + 1, dp[i - 3] * 2, dp[i - 4] * 3, dp[i - 5] * 4)
            }
        }
        return dp[n]
    }
}