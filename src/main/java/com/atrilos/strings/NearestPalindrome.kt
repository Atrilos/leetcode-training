package com.atrilos.strings

import kotlin.math.abs
import kotlin.math.pow

/**
 * [564](https://leetcode.com/problems/find-the-closest-palindrome/description/)
 */
class NearestPalindrome {
    fun nearestPalindromic(n: String): String {
        val nDigits = n.length
        val num = n.toLong()
        val i = if (nDigits % 2 == 0) nDigits / 2 - 1 else nDigits / 2
        val left = n.substring(0, i+1).toLong()
        val candidates = arrayOf(
            (left - 1).pal(nDigits % 2 != 0),
            (left).pal(nDigits % 2 != 0),
            (left + 1).pal(nDigits % 2 != 0),
            10.0.pow(nDigits.toDouble()).toLong() + 1,
            10.0.pow(nDigits - 1.0).toLong() - 1
        )

        var minDiff = Long.MAX_VALUE
        var minPal = 0L
        for (candidate in candidates) {
            if (candidate == num) continue

            val diff = abs(num - candidate)
            if (diff < minDiff || diff == minDiff && candidate < minPal) {
                minDiff = diff
                minPal = candidate
            }
        }

        return "$minPal"
    }

    private fun Long.pal(odd: Boolean): Long {
        var result = this
        var n = if (odd) this / 10 else this
        while (n > 0) {
            result *= 10
            result += n % 10
            n /= 10
        }

        return result
    }
}