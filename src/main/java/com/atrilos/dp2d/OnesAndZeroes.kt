package com.atrilos.dp2d

/**
 * [474](https://leetcode.com/problems/ones-and-zeroes/description/)
 */
class OnesAndZeroes {
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val binaryToCounter = hashMapOf<String, Pair<Int, Int>>() // 0 to 1
        for (str in strs) {
            if (str in binaryToCounter) continue

            var zeros = 0
            var ones = 0
            for (ch in str) {
                if (ch == '0') {
                    zeros++
                } else {
                    ones++
                }
            }
            binaryToCounter[str] = zeros to ones
        }

        val dp = Array(m + 1) { IntArray(n + 1) }
        for (str in strs) {
            val (zeros, ones) = binaryToCounter[str]!!
            for (mIdx in m downTo zeros) {
                for (nIdx in n downTo ones) {
                    dp[mIdx][nIdx] = maxOf(dp[mIdx][nIdx], dp[mIdx - zeros][nIdx - ones] + 1)
                }
            }
        }
        return dp[m][n]
    }
}