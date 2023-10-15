package com.atrilos.dp1d

import kotlin.math.pow

/**
 * [343](https://leetcode.com/problems/integer-break/description/)
 */
class IntegerBreak {
    fun integerBreakMath(n: Int): Int {
        return if (n <= 3) {
            n - 1
        } else if (n % 3 == 0) {
            3.0.pow((n / 3).toDouble()).toInt()
        } else if (n % 3 == 1) {
            3.0.pow((n / 3 - 1).toDouble()).toInt() * 4
        } else {
            3.0.pow((n / 3).toDouble()).toInt() * 2
        }
    }

    private lateinit var dp: IntArray
    fun integerBreakDp(n: Int): Int {
        if (n <= 3) {
            return n - 1
        }
        dp = IntArray(n + 1)

        dfs(n)

        return dp[n]
    }

    private fun dfs(n: Int): Int {
        if (n <= 3) {
            return n
        }
        if (dp[n] != 0) {
            return dp[n]
        }
        var ans = 0
        for (i in 2..<n - 1) {
            ans = maxOf(ans, i * dfs(n - i))
        }
        dp[n] = ans

        return ans
    }
}

fun main() {
    val integerBreak = IntegerBreak()
    for (i in 2..20) {
        val integerBreakDp = integerBreak.integerBreakDp(i)
        val integerBreakMath = integerBreak.integerBreakMath(i)
        if (integerBreakDp == integerBreakMath) {
            println("$integerBreakDp == $integerBreakMath")
        } else {
            println("$integerBreakDp != $integerBreakMath")
            break
        }
    }
}