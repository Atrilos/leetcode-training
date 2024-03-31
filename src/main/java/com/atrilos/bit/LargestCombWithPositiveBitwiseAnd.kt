package com.atrilos.bit

/**
 * [2275](https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/description/)
 */
class LargestCombWithPositiveBitwiseAnd {
    fun largestCombination(candidates: IntArray): Int {
        val countBits = IntArray(32)
        for (candidate in candidates) {
            for (j in 0..31) {
                if (candidate ushr j and 1 == 1) {
                    countBits[j]++
                }
            }
        }
        return countBits.max()
    }
}
