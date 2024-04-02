package com.atrilos.stack

/**
 * [2334](https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold/description/)
 */
class ValidSubarraySize {
    fun validSubarraySize(nums: IntArray, threshold: Int): Int {
        val n = nums.size
        val nextSmall = IntArray(n) { n }
        val prevSmall = IntArray(n) { -1 }
        val stack = java.util.ArrayDeque<Int>()

        for (i in nums.indices) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop()
            }
            if (stack.isNotEmpty()) {
                prevSmall[i] = stack.peek()
            }
            stack.push(i)
        }

        stack.clear()

        for (i in nums.indices.reversed()) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop()
            }
            if (stack.isNotEmpty()) {
                nextSmall[i] = stack.peek()
            }
            stack.push(i)
        }

        for (i in nums.indices) {
            val len = nextSmall[i] - prevSmall[i] - 1
            if (nums[i] > threshold / len) {
                return len
            }
        }

        return -1
    }
}