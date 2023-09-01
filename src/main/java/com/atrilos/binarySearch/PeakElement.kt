package com.atrilos.binarySearch

/**
 * 162. Find Peak Element
 */
fun main() {
    println(findPeakElement(intArrayOf(5, 6, 1, 2, 3, 4)))
}
fun findPeakElement(nums: IntArray): Int {
    var leftIndex = 0
    var rightIndex = nums.lastIndex

    while (leftIndex < rightIndex) {
        val midIndex = leftIndex + (rightIndex - leftIndex) / 2
        when {
            nums[midIndex] > nums[midIndex + 1] -> rightIndex = midIndex
            nums[midIndex] < nums[midIndex + 1] -> leftIndex = midIndex + 1
        }
    }
    return leftIndex
}