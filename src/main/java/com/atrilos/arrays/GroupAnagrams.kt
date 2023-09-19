package com.atrilos.arrays

/**
 * [49](https://leetcode.com/problems/group-anagrams/)
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val sortedStrs = mutableListOf<String>()
    strs.forEach { sortedStrs.add(String(it.toCharArray().sortedArray())) }

    val map = mutableMapOf<String, MutableList<String>>()
    for ((i, str) in sortedStrs.withIndex()) {
        map.merge(str, mutableListOf(strs[i])) { v1, v2 -> v1.apply { addAll(v2) } }
    }

    return map.values.toList()
}