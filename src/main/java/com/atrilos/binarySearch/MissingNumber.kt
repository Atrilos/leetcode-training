package com.atrilos.binarySearch

/**
 * [1228](https://leetcode.com/problems/missing-number-in-arithmetic-progression/description/)
 */
fun missingNumber(arr: IntArray): Int {
    val diff = (arr.last() - arr[0]) / arr.size
    var l = 0
    var r = arr.lastIndex

    while (l < r) {
        val mid = l + (r - l) / 2

        if (arr[mid] == arr[0] + mid * diff) {
            l = mid + 1
        } else {
            r = mid
        }
    }

    return arr[0] + l * diff
}