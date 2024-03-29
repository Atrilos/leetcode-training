package com.atrilos.arrays

/**
 * [26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
 */
fun removeDuplicates(nums: IntArray): Int {
    var i = 0
    var l = 0
    var r = 0
    while (r < nums.size) {
        while (r < nums.size && nums[r] == nums[l]) {
            r++
        }
        nums[i] = nums[l]
        i++
        l = r
    }
    return i
}