package com.atrilos.graphs

/**
 * [4](https://leetcode.com/problems/median-of-two-sorted-arrays/description/)
 */
class MedianOfTwoSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val n1 = nums1.size
        val n2 = nums2.size

        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1)
        }

        val odd = (n1 + n2) % 2 == 1
        val half = (n1 + n2 + 1) / 2
        var l = 0
        var r = n1

        while (l < r) {
            val m1 = l + (r - l) / 2
            val m2 = half - m1

            if (nums1[m1] < nums2[m2 - 1])
                l = m1 + 1
            else
                r = m1
        }

        // must be 4 nums of A[l-1], A[l], B[half-l], and B[half-l-1]
        val m1 = l
        val m2 = half - m1

        // odd: median = max of A[l-1] and B[half-l-1]
        val c1 = maxOf(if (m1 <= 0) Int.MIN_VALUE else nums1[m1 - 1],
            if (m2 <= 0) Int.MIN_VALUE else nums2[m2 - 1])

        if (odd) {
            return c1.toDouble()
        }

        // even: median = (max(A[l-1], B[half-l-1]) + min(A[l], B[half-l]) / 2.0
        val c2 = minOf(if (m1 >= n1) Int.MAX_VALUE else nums1[m1],
            if (m2 >= n2) Int.MAX_VALUE else nums2[m2])

        return (c1 + c2).toDouble() / 2
    }
}