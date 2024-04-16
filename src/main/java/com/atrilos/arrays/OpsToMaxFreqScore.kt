package com.atrilos.arrays

/**
 * [2968](https://leetcode.com/problems/apply-operations-to-maximize-frequency-score/description/)
 */
class OpsToMaxFreqScore {
    fun maxFrequencyScore(nums: IntArray, k: Long): Int {
        nums.sort()
        var left = 0
        var maxFreq = 1
        var cost = 0L
        var med = 0

        for (right in 1..nums.lastIndex) {
            cost += (nums[right] - nums[med]).toLong()
            med = (left + right + 1) / 2

            while (cost > k) {
                cost -= (nums[med] - nums[left]).toLong()
                left++
                med = (left + right + 1) / 2
            }

            maxFreq = maxOf(maxFreq, (right - left + 1))
        }

        return maxFreq
    }
}