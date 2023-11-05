package com.atrilos.dp1d

/**
 * [1259](https://leetcode.com/problems/handshakes-that-dont-cross/description/)
 */
class HandshakesDontCross {
    fun numberOfWays(numPeople: Int): Int {
        val mod = 1000000007L
        val dp = LongArray(numPeople / 2 + 1)
        dp[0] = 1
        for (i in 1..numPeople / 2) {
            for (j in 0..<i) {
                dp[i] += dp[j] * dp[i - j - 1] % mod
                dp[i] %= mod
            }
        }
        return dp.last().toInt()
    }
}