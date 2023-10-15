package com.atrilos.binarySearch

/**
 * [1060](https://leetcode.com/problems/missing-element-in-sorted-array/)
 */
fun missingElement(arr: IntArray, k: Int): Int {
    var l = 0
    var r = arr.lastIndex

    while (l < r) {
        val mid = l + (r - l + 1) / 2
        if (arr[mid] - arr[0] - mid < k) {
            l = mid
        } else {
            r = mid - 1
        }
    }
    return arr[0] + l + k
}