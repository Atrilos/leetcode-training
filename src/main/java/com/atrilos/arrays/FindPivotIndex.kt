package com.atrilos.arrays

fun pivotIndex(nums: IntArray): Int {
    var right = nums.sum()
    var left = 0

    nums.forEachIndexed { index, i ->
        if (left == right - i) return index
        left += i
        right -= i
    }

    return -1
}