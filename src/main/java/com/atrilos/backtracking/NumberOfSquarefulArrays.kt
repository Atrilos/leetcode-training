package com.atrilos.backtracking

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * [996](https://leetcode.com/problems/number-of-squareful-arrays/description/)
 */
class NumberOfSquarefulArrays {
    private var full = 0

    fun numSquarefulPerms(nums: IntArray): Int {
        full = (1 shl nums.size) - 1
        var res = 0
        val seen = mutableSetOf<String>()

        for ((i, num) in nums.withIndex()) {
            res += backtrack(nums, 1 shl i, num, "$num", seen)
        }

        return res
    }

    private fun backtrack(nums: IntArray, used: Int, last: Int, key: String, seen: MutableSet<String>): Int {
        if (key in seen) {
            return 0
        }
        if (used == full) {
            seen += key
            return 1
        }
        var count = 0
        for ((i, num) in nums.withIndex()) {
            val mask = used or (1 shl i)
            if (mask == used) continue
            if (isPerfectSquare(num + last)) {
                count += backtrack(nums, mask, num, key + "#${num}", seen)
            }
        }
        seen += key
        return count
    }

    private fun isPerfectSquare(num: Int): Boolean = ceil(sqrt(num.toDouble())) == floor(sqrt(num.toDouble()))
}

class NumberOfSquarefulArraysHamilton {
    fun numSquarefulPerms(nums: IntArray): Int {
        val n = nums.size
        val graph = MutableList(n) { mutableListOf<Int>() }

        for (i in nums.indices) {
            for (j in i + 1..<n) {
                if (isPerfectSquare(nums[i] + nums[j])) {
                    graph[i].add(j)
                    graph[j].add(i)
                }
            }
        }

        val dp = Array(1 shl n) { IntArray(n) }

        for (i in nums.indices) {
            dp[1 shl i][i] = 1
        }

        for (mask in 3..<(1 shl n)) {
            for (i in nums.indices) {
                if (mask and (1 shl i) != 0) {
                    for (j in graph[i]) {
                        if (mask and (1 shl j) != 0) {
                            dp[mask][i] += dp[mask xor (1 shl i)][j]
                        }
                    }
                }
            }
        }

        var res = 0
        for (i in nums.indices) {
            res += dp[(1 shl n) - 1][i]
        }

        for (i in nums.indices) {
            var k = 1
            for (j in i + 1..<n) {
                if (nums[i] == nums[j]) k++
            }
            res /= k
        }

        return res
    }

    private fun isPerfectSquare(num: Int): Boolean = ceil(sqrt(num.toDouble())) == floor(sqrt(num.toDouble()))
}