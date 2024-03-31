package com.atrilos.dp2d

import kotlin.math.abs

/**
 * [935](https://leetcode.com/problems/knight-dialer/description/)
 */
class KnightDialer {
    companion object {
        private val srcMap: Map<Int, List<Int>> = mapOf(
            1 to listOf(6, 8),
            2 to listOf(7, 9),
            3 to listOf(4, 8),
            4 to listOf(0, 9, 3),
            5 to listOf(),
            6 to listOf(1, 7, 0),
            7 to listOf(2, 6),
            8 to listOf(1, 3),
            9 to listOf(2, 4),
            0 to listOf(4, 6),
        )
        private const val MOD = 1_000_000_007
    }

    fun knightDialer(n: Int): Int {
        var dpPrev = IntArray(10) { 1 }

        for (i in 1..<n) {
            val dpCurr = IntArray(10) { 0 }
            for (j in 0..9) {
                for (src in srcMap[j]!!) {
                    dpCurr[j] = (dpCurr[j] + dpPrev[src]) % MOD
                }
            }
            dpPrev = dpCurr
        }

        return dpPrev.fold(0) { acc, i -> (acc + i) % MOD }
    }
}

