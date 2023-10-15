package com.atrilos.binarySearch


/**
 * [153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/)
 */
fun findMin(nums: IntArray): Int {
    var l = 0
    var r = nums.lastIndex
    var min = Int.MAX_VALUE

    while (l <= r) {
        val mid = (l + r) / 2
        if (nums[mid] < min) {
            min = nums[mid]
        }
        if (nums[mid] < nums[r]) {
            r = mid - 1
        } else {
            l = mid + 1
        }
    }

    return min
}