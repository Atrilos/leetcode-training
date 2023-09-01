package com.atrilos.strings

import java.util.*

/**
 * https://leetcode.com/problems/reorganize-string/
 * 767
 */
fun reorganizeString(s: String): String {
    val chars = mutableListOf<Char>()
    val freqMap = mutableMapOf<Char, Int>()
    val maxHeap = PriorityQueue<MutableMap.MutableEntry<Char, Int>>(compareByDescending { it.value })

    s.groupingBy { it }.eachCountTo(freqMap)
    maxHeap.addAll(freqMap.entries)
    while (!maxHeap.isEmpty()) {
        val entry = maxHeap.remove()

        if (chars.isNotEmpty() && entry.key == chars.last()) {
            if (maxHeap.isEmpty()) return ""

            val entry2 = maxHeap.remove()

            chars.add(entry2.key)
            if (entry2.value > 1) {
                entry2.setValue(entry2.value - 1)
                maxHeap.add(entry2)
            }
            maxHeap.add(entry)
        } else {
            chars.add(entry.key)
            if (entry.value > 1) {
                entry.setValue(entry.value - 1)
                maxHeap.add(entry)
            }
        }
    }

    return chars.joinToString(separator = "")
}