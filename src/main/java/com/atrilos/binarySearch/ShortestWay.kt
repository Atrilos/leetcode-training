package com.atrilos.binarySearch

/**
 * [1055](https://leetcode.com/problems/shortest-way-to-form-string/description/)
 */
fun shortestWay(source: String, target: String): Int {
    // List of indices for all characters in source
    val charToIndices = mutableMapOf<Char, MutableList<Int>>()
    for ((i, c) in source.withIndex()) {
        charToIndices.getOrPut(c) { mutableListOf() }.add(i)
    }

    // Pointer for source
    var sourceIterator = 0

    // Number of times we need to iterate through source
    var count = 1

    // Find all characters of target in source
    for (c in target) {
        // If the character is not in the source, return -1
        if (!charToIndices.containsKey(c)) {
            return -1
        }

        // Binary search to find the index of the character in source
        // next to the source iterator
        val indices = charToIndices[c]!!
        val index = binarySearch(indices, sourceIterator)

        // If we have reached the end of the list, we need to iterate
        // through source again, hence first index of character in source.
        if (index == indices.size) {
            count += 1
            sourceIterator = indices[0] + 1
        } else {
            sourceIterator = indices[index] + 1
        }
    }

    // Return the number of times we need to iterate through source
    return count
}

// Binary search to find the index of the character in source
// next to the source iterator
private fun binarySearch(arr: List<Int>, target: Int): Int {
    var left = 0
    var right = arr.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (arr[mid] == target) {
            return mid
        } else if (arr[mid] < target) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return left
}