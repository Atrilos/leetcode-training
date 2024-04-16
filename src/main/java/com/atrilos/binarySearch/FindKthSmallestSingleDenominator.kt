package com.atrilos.binarySearch

import kotlin.math.pow

/**
 * [3116](https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/description/)
 */
class FindKthSmallestSingleDenominator {
    fun findKthSmallest(coins: IntArray, k: Int): Long {
        val lcms = mutableListOf<Long>()
        val n = 1 shl coins.size

        for (mask in 1 until n) {
            var lcm = 1L

            for (j in coins.indices) {
                if ((mask and (1 shl j)) > 0) {
                    lcm = lcm(lcm, coins[j].toLong())
                }
            }

            lcms.add(lcm * (if (Integer.bitCount(mask) % 2 == 1) 1 else -1))
        }

        var low = 1L
        var high = 10.0.pow(11.0).toLong() // 10^11 > (25 * 2)*10^9

        while (low < high) {
            val mid = low + (high - low) / 2

            if (count(mid, lcms) < k) {
                low = mid + 1
            } else {
                high = mid
            }
        }

        return low
    }

    private fun count(target: Long, lcms: List<Long>): Long {
        var count = 0L

        for (lcm in lcms) {
            count += target / lcm
        }

        return count
    }

    private fun gcd(a: Long, b: Long): Long {
        if (b == 0L) {
            return a
        }
        return gcd(b, a % b)
    }

    private fun lcm(a: Long, b: Long): Long {
        return a * b / gcd(a, b)
    }
}

fun main() {
    println(FindKthSmallestSingleDenominator().findKthSmallest(intArrayOf(3, 6, 9), 3))
    println(FindKthSmallestSingleDenominator().findKthSmallest(intArrayOf(5, 2), 7))
}