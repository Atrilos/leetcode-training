package com.atrilos.dp1d

/**
 * https://leetcode.com/problems/domino-and-tromino-tiling/
 * 790
 */
fun numTilings(n: Int): Int {
    return when (n) {
        1, 2 -> n
        3 -> 5
        else -> {
            val mod = (1e9 + 7).toLong()
            val dp = LongArray(n)
            dp[0] = 1
            dp[1] = 2
            dp[2] = 5
            for (i in 3 until n) {
                dp[i] = (2 * dp[i - 1] + dp[i - 3]) % mod
            }
            (dp[n - 1] % mod).toInt()
        }
    }
}