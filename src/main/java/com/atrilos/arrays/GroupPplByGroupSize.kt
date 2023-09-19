package com.atrilos.arrays

/**
 * [1282](https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/)
 */
fun groupThePeople(groupSizes: IntArray): List<List<Int>> {
    val cache = HashMap<Int, MutableList<Int>>()
    val result = ArrayList<List<Int>>()

    groupSizes.forEachIndexed { value, group ->
        val g = cache.getOrPut(group) { mutableListOf() }
        g.add(value)
        if(g.size == group) result.add(cache.remove(group)!!)
    }

    return result
}