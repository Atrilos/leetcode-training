package com.atrilos.binarySearch

/**
 * [1150](https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/)
 */
class IsMajorityElement {
    fun isMajorityElement(nums: IntArray, target: Int): Boolean {
        val left = binaryLeft(nums, target)
        return left + nums.size / 2 < nums.size && nums[left + nums.size / 2] == target
    }

    private fun binaryLeft(nums: IntArray, target: Int): Int {
        var lo = 0
        var hi = nums.lastIndex

        while (lo < hi) {
            val mid = (lo + hi) / 2
            if (nums[mid] >= target) {
                hi = mid
            } else {
                lo = mid + 1
            }
        }

        return lo
    }
}