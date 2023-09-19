package com.atrilos.arrays

/**
 * [80](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/)
 */
fun removeDuplicates2(nums: IntArray): Int {
    var i = 0
    var l = 0
    var r = 0
    while (r < nums.size) {
        var duplicates = 1
        while (r < nums.size && nums[r] == nums[l]) {
            duplicates++
            r++
        }
        repeat(minOf(duplicates, 2)) {
            nums[i++] = nums[l]
        }
        l = r
    }
    return i
}