package com.atrilos.dp2d

/**
 * [516](https://leetcode.com/problems/longest-palindromic-subsequence/description/)
 */
class LongestPalindromeSubseq {
    // Bottom-up reversed LCS
    fun longestPalindromeSubseqReversedLCS(s: String): Int {
        val dp = Array(s.length + 1) { IntArray(s.length + 1) }

        for (i in s.indices) {
            for (j in s.indices.reversed()) {
                val reversedJ = s.lastIndex - j
                if (s[i] == s[j]) {
                    dp[i + 1][reversedJ + 1] = dp[i][reversedJ] + 1
                } else {
                    dp[i + 1][reversedJ + 1] = maxOf(dp[i + 1][reversedJ], dp[i][reversedJ + 1])
                }
            }
        }

        return dp.last().last()
    }
    // Bottom-up memo
    fun longestPalindromeSubseqMemo(s: String): Int {
        val memo = Array(s.length) { IntArray(s.length) { -1 } }

        fun lpsHelper(low: Int, high: Int) : Int {
            if(memo[low][high] != -1) {
                return memo[low][high]
            }

            if(low == high) {
                memo[low][high] = 1
                return memo[low][high]
            }

            if(high - low == 1) {
                memo[low][high] = if(s[low] == s[high]) 2 else 1
                return memo[low][high]
            }

            if(s[high] == s[low]) {
                memo[low][high] = 2 + lpsHelper(low + 1, high - 1)
                return memo[low][high]
            }

            memo[low][high] = maxOf(
                lpsHelper(low + 1, high),
                lpsHelper(low, high - 1)
            )
            return memo[low][high]
        }

        return lpsHelper(0, s.lastIndex)
    }
}