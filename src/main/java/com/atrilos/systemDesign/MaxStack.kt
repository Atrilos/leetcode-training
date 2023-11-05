package com.atrilos.systemDesign

import java.util.*

/**
 * [716](https://leetcode.com/problems/max-stack/)
 */
class MaxStack {

    val stack: TreeSet<Pair<Int, Int>>
    val values: TreeSet<Pair<Int, Int>>
    var index = 0

    init {
        val comp = compareBy<Pair<Int, Int>>({ it.first }, { it.second })
        stack = sortedSetOf(comp)
        values = sortedSetOf(comp)
    }

    fun push(x: Int) {
        stack.add(index to x)
        values.add(x to index)
        index++
    }

    fun pop(): Int {
        val (key, value) = stack.pollLast()!!
        values.remove(value to key)
        return value
    }

    fun top(): Int {
        return stack.last().second
    }

    fun peekMax(): Int {
        return values.last().first
    }

    fun popMax(): Int {
        val (value, key) = values.pollLast()!!
        stack.remove(key to value)
        return value
    }

}