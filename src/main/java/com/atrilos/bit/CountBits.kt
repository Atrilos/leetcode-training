package com.atrilos.bit

/**
 * https://leetcode.com/problems/counting-bits/
 * 338
 */
fun countBits(n: Int): IntArray {
    val array = IntArray(n + 1)

    for (i in 0..n) {
        array[i] = when (i) {
            0 -> 0
            1 -> 1
            else -> {
                array[i ushr 1] + (i and 1)
            }
        }
    }

    return array
}