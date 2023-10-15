package com.atrilos.dp2d

/**
 * [1420](https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/description/)
 */
class BuildArrayWithKMaxFromLeftToRight {
    fun numOfArrays(n: Int, m: Int, k: Int): Int {
        val dp = Array(n) { Array(m + 1) { IntArray(k + 1) { -1 } } }
        val mod = (1e9 + 7).toInt()

        fun dfs(cnt: Int, maxSoFar: Int, remain: Int): Int {
            if (cnt == n) {
                if (remain == 0) {
                    return 1
                }
                return 0
            }
            if (remain < 0) {
                return 0
            }
            if (dp[cnt][maxSoFar][remain] != -1) {
                return dp[cnt][maxSoFar][remain]
            }

            var res = 0
            for (i in 1..maxSoFar) {
                res = (res + dfs(cnt + 1, maxSoFar, remain)) % mod
            }
            for (i in maxSoFar + 1..m) {
                res = (res + dfs(cnt + 1, i, remain - 1))
            }
            dp[cnt][maxSoFar][remain] = res
            return res
        }

        return dfs(0, 1, k)
    }
}