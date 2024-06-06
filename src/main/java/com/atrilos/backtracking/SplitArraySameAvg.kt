package com.atrilos.backtracking

/**
 * [805](https://leetcode.com/problems/split-array-with-same-average/description/)
 */
class SplitArraySameAvg {
    fun splitArraySameAverage(nums: IntArray): Boolean {
        if (nums.size == 1) {
            return false
        }
        val n = nums.size
        val sum = nums.sum()
        // We divide the given array into 2 parts , where n1 = length of part-1 & s1 = sum of elements of part-1
        // s1 = (sum*n1)/n , since the average of both part-1 & part-2 are equal
        // Range of n1 = [ 1 , n-1 ]
        // We iterate over each possible valid value of n1 , and check if the corresponding subseq of sum = s1 & length = n1 is possible or not
        for (n1 in 1..<n) {
            val numerator = (sum * n1)
            val s1 = numerator / n
            // if the s1 = float value , then we move on to the next possible n1
            if (numerator % n != 0) continue
            //if s1 = integer , then we check if there exists a subseq whose sum = s1 , we check this by dp
            val dp = Array(n + 1) { Array<Boolean?>(s1 + 1) { null } }
            if (isPresent(s1, n1, nums, nums.lastIndex, dp)) {
                return true
            }
        }
        return false
    }


    // function that check if there exists a subseq. of length=n1 & sum=s1
    private fun isPresent(
        target: Int,
        count: Int,
        nums: IntArray,
        pos: Int,
        dp: Array<Array<Boolean?>>
    ): Boolean {
        if (pos < 0) {
            return target == 0 && count == 0
        }
        if (count == 0) {
            return target == 0
        }
        if (dp[pos][target] != null) {
            return dp[pos][target]!!
        }
        if (nums[pos] > target) {
            val exclude = isPresent(target, count, nums, pos - 1, dp)
            dp[pos][target] = exclude
            return dp[pos][target]!!
        } else {
            val include = isPresent(target - nums[pos], count - 1, nums, pos - 1, dp)
            val exclude = isPresent(target, count, nums, pos - 1, dp)
            dp[pos][target] = include || exclude
            return dp[pos][target]!!
        }
    }
}