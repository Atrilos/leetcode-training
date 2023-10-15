package com.atrilos.slidingWindow

/**
 * [2009](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/description/)
 */
class MinOperationsToMakeContinuous {
    fun minOperations(nums: IntArray): Int {
        val unique = sortedSetOf<Int>()
        for (num in nums) {
            unique.add(num)
        }
        val newNums = unique.toIntArray()

        var maxCount = 1
        var firstNumIndex = 0
        for (i in 1..newNums.lastIndex) {
            while (newNums[i] - newNums[firstNumIndex] >= nums.size) {
                ++firstNumIndex
            }
            maxCount = maxOf(maxCount, i - firstNumIndex + 1)
        }

        return nums.size - maxCount
    }
}