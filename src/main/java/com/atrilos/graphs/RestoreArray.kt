package com.atrilos.graphs

/**
 * [1743](https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/)
 */
class RestoreArray {
    fun restoreArray(adjacentPairs: Array<IntArray>): IntArray {
        // Node to neighbors
        val map = mutableMapOf<Int, MutableSet<Int>>()

        adjacentPairs.forEach { pair ->
            map[pair[0]] = map.getOrDefault(pair[0], mutableSetOf()).also { it.add(pair[1]) }
            map[pair[1]] = map.getOrDefault(pair[1], mutableSetOf()).also { it.add(pair[0]) }
        }

        val result = IntArray(map.size)
        val entry = map.entries.first { it.value.size == 1 }
        result[0] = entry.key
        var current = entry.value.first()

        for (i in 1..<result.size - 1) {
            result[i] = current
            current = map[current]!!.first { it != result[i - 1] }
        }
        result[result.size - 1] = current

        return result
    }
}