package com.atrilos.stack

import java.util.ArrayDeque

/**
 * https://leetcode.com/problems/implement-stack-using-queues/
 * 225
 */
class MyStack() {

    val elements = ArrayDeque<Int>()

    // push to top
    fun push(x: Int) {
        elements.offerLast(x)
        repeat(elements.size - 1) {
            elements.offerLast(elements.pop())
        }
    }

    // pop from front
    fun pop(): Int {
        return elements.pop()
    }

    // peek from front
    fun top(): Int {
        return elements.peek()
    }

    // isEmpty
    fun empty(): Boolean {
        return elements.isEmpty()
    }

}