package com.atrilos.systemDesign

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     constructor()
 *
 *     // Constructor initializes a single integer.
 *     constructor(value: Int)
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     fun isInteger(): Boolean
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     fun getInteger(): Int?
 *
 *     // Set this NestedInteger to hold a single integer.
 *     fun setInteger(value: Int): Unit
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     fun add(ni: NestedInteger): Unit
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     fun getList(): List<NestedInteger>?
 * }
 */

/**
 * [341](https://leetcode.com/problems/flatten-nested-list-iterator/)
 */
class NestedIterator(nestedList: List<NestedInteger>) {
    class NestedInteger() {
        constructor(value: Int): this()

        fun isInteger(): Boolean {
            TODO()
        }

        fun getInteger(): Int? {
            TODO()
        }

        fun setInteger(value: Int): Unit {

        }

        fun add(ni: NestedInteger): Unit {

        }

        fun getList(): List<NestedInteger>? {
            TODO()
        }
    }
    val stack = java.util.ArrayDeque(nestedList)
    fun next(): Int {
        val next = stack.removeFirst()
        return next.getInteger()!!
    }

    fun hasNext(): Boolean {
        while (stack.isNotEmpty()) {
            val next = stack.removeFirst()
            if (next.isInteger()) {
                stack.offerFirst(next)
                return true
            } else {
                val list = next.getList()
                for (i in list!!.indices.reversed()) {
                    stack.offerFirst(list[i])
                }
            }
        }
        return false
    }
}