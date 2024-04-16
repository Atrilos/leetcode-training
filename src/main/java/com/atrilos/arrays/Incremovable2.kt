package com.atrilos.arrays

/**
 * [2972](https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-ii/description/)
 */
class Incremovable2 {
    fun incremovableSubarrayCount(nums: IntArray): Long {
        val arrayLength = nums.size

        var left = 0
        var right = arrayLength - 1

        while (left + 1 < arrayLength && nums[left + 1] > nums[left]) {
            left++
        }

        while (right - 1 >= 0 && nums[right] > nums[right - 1]) {
            right--
        }

        if (left == arrayLength - 1) {
            return (arrayLength * (arrayLength + 1) / 2).toLong()
        }

        // delete all from beginning till right pointer
        var res = (arrayLength - right + 1).toLong()

        // delete all from i+1 till right pointer or incr right pointer, essentially mono
        for (i in 0..left) {
            while (right < arrayLength && nums[i] >= nums[right]) {
                right++
            }

            res += (arrayLength - right + 1).toLong()
        }

        return res
    }
}