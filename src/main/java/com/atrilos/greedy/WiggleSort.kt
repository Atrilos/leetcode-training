package com.atrilos.greedy

/**
 * [280](https://leetcode.com/problems/wiggle-sort/)
 */
fun wiggleSort(nums: IntArray) {
    for (i in 0..<nums.lastIndex) {
        if ((i % 2 == 0 && nums[i + 1] < nums[i]) || (i % 2 != 0 && nums[i + 1] > nums[i])) {
            nums[i] = nums[i + 1].also { nums[i + 1] = nums[i] }
        }
    }
}