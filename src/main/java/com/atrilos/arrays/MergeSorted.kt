package com.atrilos.arrays

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * 88
 */
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    var i = m - 1
    var j = n - 1
    var k = n + m - 1
    while (j >= 0) {
        nums1[k--] = if (i < 0 || nums2[j] > nums1[i]) nums2[j--] else nums1[i--]
    }
}