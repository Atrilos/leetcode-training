package com.atrilos.binarySearch

/**
 * [34](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)
 */
class FindFirstAndLastInSorted {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        var l = 0
        var r = nums.size - 1
        var lower = -1
        while (l <= r) {
            val mid = (l + r) / 2
            if (nums[mid] == target) {
                lower = mid
                r = mid - 1
            } else if (nums[mid] > target) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        l = 0
        r = nums.size - 1
        var upper = -1
        while (l <= r) {
            val mid = (l + r) / 2
            if (nums[mid] == target) {
                upper = mid
                l = mid + 1
            } else if (nums[mid] < target) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }

        return intArrayOf(lower, upper)
    }
}