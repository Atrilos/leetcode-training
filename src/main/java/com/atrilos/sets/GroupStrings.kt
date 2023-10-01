package com.atrilos.sets

/**
 * [249](https://leetcode.com/problems/group-shifted-strings/description/)
 */
fun groupStrings(strings: Array<String>): List<List<String>> {

    // Normalize strings
    val pair = strings.map { str ->
        // Convert each String to CharArray and then Int
        val asciiInt = str.map { c -> c.code }

        // Subtract every Int by the first value to normalize all the asciiInt
        val firstAsciiInt = asciiInt.first()
        val normalizedInt = asciiInt.map { int ->
            (int - firstAsciiInt + 26) % 26
        }

        normalizedInt to str
    }

    // Group same type of strings
    val hMap = hashMapOf<List<Int>, MutableList<String>>()
    for (p in pair) {
        val int = p.first
        val str = p.second
        hMap.getOrPut(int) { mutableListOf() }.add(str)
    }

    return hMap.values.toList()
}