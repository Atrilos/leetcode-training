package com.atrilos.dp1d

/**
 * https://leetcode.com/problems/n-th-tribonacci-number/
 * 1137
 */

fun main() {
    println(tribonacci(36))
}

fun tribonacci(n: Int): Int {
    return when (n) {
        0 -> 0
        1, 2 -> 1
        else -> {
            var first = 0
            var second = 1
            var third = 1
            for (i in 3..n) {
                val temp = third
                third += first + second
                first = second
                second = temp
            }
            third
        }
    }
}