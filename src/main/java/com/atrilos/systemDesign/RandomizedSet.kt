package com.atrilos.systemDesign

/**
 * [380](https://leetcode.com/problems/insert-delete-getrandom-o1/)
 */
class RandomizedSet {

    val map = HashMap<Int, Int>()
    val list = mutableListOf<Int>()

    fun insert(`val`: Int): Boolean {
        if (map.contains(`val`)) {
            return false
        }
        list.add(`val`)
        map[`val`] = list.lastIndex

        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!map.contains(`val`)) {
            return false
        }
        val index = map[`val`]!!
        val lastVal = list.last()
        list[index] = lastVal
        list.removeLast()
        map[lastVal] = index
        map.remove(`val`)

        return true
    }

    fun getRandom(): Int {
        return list[(0..list.lastIndex).random()]
    }
}