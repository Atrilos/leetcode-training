package com.atrilos.binarySearch

/**
 * [162](https://leetcode.com/problems/find-peak-element/)
 */
fun main() {
    println(findPeakElement(intArrayOf(5, 6, 1, 2, 3, 4)))
}
fun findPeakElement(nums: IntArray): Int {
    var l = 0
    var r = nums.lastIndex

    while (l < r) {
        val mid = l + (r - l) / 2
        if (nums[mid] > nums[mid + 1]) {
            r = mid
        } else {
            l = mid + 1
        }
    }
    return l
}