package com.atrilos.stack

import java.util.*

/**
 * [456](https://leetcode.com/problems/132-pattern/description/)
 */
fun find132pattern(nums: IntArray): Boolean {
    if (nums.size < 3) return false
    val stack = LinkedList<Int>()
    val min = IntArray(nums.size)
    min[0] = nums[0]
    for (i in 1 until nums.size) {
        min[i] = minOf(min[i - 1], nums[i])
    }
    for (j in nums.indices.reversed()) {
        if (nums[j] > min[j]) {
            while (!stack.isEmpty() && stack.peek() <= min[j]) {
                stack.pop()
            }
            if (!stack.isEmpty() && stack.peek() < nums[j]) {
                return true
            }
            stack.push(nums[j])
        }
    }
    return false
}