package com.atrilos.arrays

/**
 * [992](https://leetcode.com/problems/subarrays-with-k-different-integers/description/)
 */
class SubarraysWithKDistinct {
    fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
        return atMostK(nums, k) - atMostK(nums, k - 1)
    }

    private fun atMostK(nums: IntArray, k: Int): Int {
        var res = 0
        var i = 0
        var count = k
        val freq = mutableMapOf<Int, Int>()

        for (j in nums.indices) {
            if ((freq[nums[j]] ?: 0) == 0) count--
            freq.merge(nums[j], 1, Int::plus)

            while (count < 0) {
                freq.merge(nums[i], -1, Int::plus)
                if (freq[nums[i]]!! == 0) count++
                i++
            }

            res += j - i + 1
        }

        return res
    }
}