package com.atrilos.dp1d

/**
 * [2266](https://leetcode.com/problems/count-number-of-texts/)
 */
class CountNumberOfTexts {
    private val mod = 1000000007
    private val dp3 = LongArray(100001)
    private val dp4 = LongArray(100001)
    private fun update() {
        var dp = dp3
        dp[1] = 1
        dp[2] = 2
        dp[3] = 4
        for (i in 4..dp.lastIndex) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
            dp[i] = dp[i] % mod
        }
        dp = dp4
        dp[0] = 1
        dp[1] = 1
        dp[2] = 2
        dp[3] = 4
        for (i in 4..dp.lastIndex) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4]
            dp[i] = dp[i] % mod
        }
    }

    fun countTexts(s: String): Int {
        update()
        var start = 0
        var end = 1
        var res = 1L
        while (start < s.length) {
            while (end < s.length && s[end] == s[start])
                ++end
            val next = if (s[start] != '7' && s[start] != '9') dp3[end - start] else dp4[end - start]
            res = (res * next) % mod
            start = end
        }
        return res.toInt()
    }
}