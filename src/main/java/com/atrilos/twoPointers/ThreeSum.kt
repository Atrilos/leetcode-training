package com.atrilos.twoPointers

/**
 * [15](https://leetcode.com/problems/3sum/description/)
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    nums.sort()
    for (i in 0..nums.size - 3) {
        if (nums[i] > 0) break
        if (i > 0 && nums[i] == nums[i - 1]) continue
        var j = i + 1
        var k = nums.lastIndex
        while (j < k) {
            val sum = nums[i] + nums[j] + nums[k]
            if (sum == 0) {
                res.add(listOf(nums[i], nums[j], nums[k]))
                k--
                j++
                while (j < k && nums[j] == nums[j - 1]) j++
            } else if (sum > 0) {
                k--
            } else {
                j++
            }
        }
    }

    return res
}
