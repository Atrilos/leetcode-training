package com.atrilos.arrays

/**
 * https://leetcode.com/problems/remove-element/
 * 27
 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    var i = 0
    var j = nums.size - 1
    var size = nums.size
    while (i <= j) {
        if (nums[i] == `val`) {
            nums[i] = nums[j].also { nums[j] = nums[i] }
            j--
            size--
        } else {
            i++
        }
    }
    return size
}