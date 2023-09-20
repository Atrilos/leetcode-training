package com.atrilos.arrays

/**
 * [1658](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/)
 */
fun minOperations(nums: IntArray, x: Int): Int {
    val target = nums.sum() - x
    var sum = 0
    var l = 0
    var ans = -1

    for(r in nums.indices) {
        sum += nums[r]
        while(l <= r && sum > target)
            sum -= nums[l++]
        if(target == sum)
            ans = maxOf(ans, r - l + 1)
    }

    return if(ans == - 1) -1 else nums.size - ans
}
