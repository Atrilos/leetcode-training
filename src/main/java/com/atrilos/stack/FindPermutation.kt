package com.atrilos.stack

import java.util.*

/**
 * [484](https://leetcode.com/problems/find-permutation/description/)
 */
fun findPermutation(s: String): IntArray {
    val stack = LinkedList<Int>().apply { add(1) }
    val res = IntArray(s.length + 1)
    var j = 0

    for ((i, c) in s.withIndex()) {
        if (c == 'I') {
            while (stack.isNotEmpty()) {
                res[j++] += stack.pop()
            }
        }
        stack.push(i + 2)
    }

    while (stack.isNotEmpty()) res[j++] += stack.pop()

    return res
}