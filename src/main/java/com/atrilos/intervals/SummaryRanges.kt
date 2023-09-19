package com.atrilos.intervals

/**
 * [228](https://leetcode.com/problems/summary-ranges/description/)
 */
fun summaryRanges(nums: IntArray): List<String> {
    if (nums.isEmpty()) return emptyList()
    val res = mutableListOf<String>()
    var i = 0
    while (i < nums.size) {
        val start = nums[i]
        var end = nums[i]
        i++
        while (i < nums.size) {
            val next = nums[i]
            if (next == end + 1) {
                end = next
            } else {
                i--
                break
            }
            i++
        }
        res += if (start == end) {
            "$start"
        } else {
            "$start->$end"
        }
        i++
    }
    return res
}