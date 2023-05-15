package com.atrilos.sets

fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
    val distinctNums1 = nums1.toSet()
    val distinctNums2 = nums2.toSet()

    return listOf((distinctNums1 - distinctNums2).toList(), (distinctNums2 - distinctNums1).toList())
}

fun uniqueOccurrences(arr: IntArray): Boolean {
    val map = arr.toList().groupingBy { it }.eachCount()
    return map.size == map.values.toSet().size
}
