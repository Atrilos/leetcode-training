package com.atrilos.dp1d

/**
 * [45](https://leetcode.com/problems/jump-game-ii/)
 */
class JumpGame2 {
    fun jump(nums: IntArray): Int {
        // The starting range of the first jump is [0, 0]
        var answer = 0
        val n = nums.size
        var curEnd = 0
        var curFar = 0
        for (i in 0 until n - 1) {
            // Update the farthest reachable index of this jump.
            curFar = maxOf(curFar, i + nums[i])

            // If we finish the starting range of this jump,
            // Move on to the starting range of the next jump.
            if (i == curEnd) {
                answer++
                curEnd = curFar
            }
        }
        return answer
    }
}