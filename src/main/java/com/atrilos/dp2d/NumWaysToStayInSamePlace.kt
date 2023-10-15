package com.atrilos.dp2d

/**
 * [1269](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/description/)
 */
class NumWaysToStayInSamePlace {
    private val mod = 1000000007
    var n = 0
    fun numWays(steps: Int, arrLen: Int): Int {
        n = minOf(arrLen, steps)
        val dp = Array(n) { IntArray(steps + 1) { -1 } }

        fun dfs(i: Int, remain: Int): Int {
            if (remain == 0) {
                if (i == 0) {
                    return 1
                }
                return 0
            }
            if (dp[i][remain] != -1) {
                return dp[i][remain]
            }
            var ans = dfs(i, remain - 1)
            if (i > 0) {
                ans = (ans + dfs(i - 1, remain - 1)) % mod
            }
            if (i < n - 1) {
                ans = (ans + dfs(i + 1, remain - 1)) % mod
            }
            dp[i][remain] = ans
            return ans
        }

        return dfs(0, steps)
    }
}