package com.atrilos.slidingWindow

fun findMaxAverage(nums: IntArray, k: Int): Double {
    var currSum = 0
    for (i in 0 until k) {
        currSum += nums[i]
    }
    var ans = currSum.toDouble() / k

    var left = 0
    var right = k - 1

    while (right < nums.lastIndex) {
        currSum -= nums[left++]
        currSum += nums[++right]
        ans = maxOf(ans, currSum.toDouble() / k)
    }

    return ans
}