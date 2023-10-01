package com.atrilos.stack

import java.util.*

/**
 * [1429](https://leetcode.com/problems/first-unique-number/description/)
 */
class FirstUnique(nums: IntArray) {

    val unique = mutableMapOf<Int, Boolean>()
    val queue = LinkedList<Int>()
    init {
        for (num in nums) {
            add(num)
        }
    }
    fun showFirstUnique(): Int {
        while (queue.isNotEmpty() && !unique[queue.peek()]!!) {
            queue.pop()
        }
        while (queue.isNotEmpty()) {
            return queue.peek()
        }
        return -1
    }

    fun add(value: Int) {
        if (value in unique) {
            unique[value] = false
        } else {
            unique[value] = true
            queue.addLast(value)
        }
    }
}