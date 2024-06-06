package com.atrilos.strings

/**
 * [31](https://leetcode.com/problems/next-permutation/description/)
 */
class NextPermutation {
    fun nextPermutation(nums: IntArray) {
        var pivot = -1
        for (r in nums.lastIndex downTo 1) {
            if (nums[r] > nums[r - 1]) {
                pivot = r - 1
                break
            }
        }
        if (pivot == -1) {
            nums.sort()
            return
        }
        for (r in nums.lastIndex downTo pivot - 1) {
            if (nums[r] > nums[pivot]) {
                nums[r] = nums[pivot].also { nums[pivot] = nums[r] }
                break
            }
        }
        nums.sort(pivot + 1, nums.size)
    }
}