package com.atrilos.graphs

/**
 * [269](https://leetcode.com/problems/alien-dictionary/description/)
 */
class AlienDict {
    fun alienOrder(words: Array<String>): String {
        val graph = mutableMapOf<Char, MutableList<Char>>()
        val inDegree = mutableMapOf<Char, Int>()

        for (word in words) {
            for (c in word) {
                inDegree[c] = 0
                graph[c] = mutableListOf()
            }
        }

        for (i in 0..<words.lastIndex) {
            val first = words[i]
            val second = words[i + 1]
            if (first.length > second.length && first.startsWith(second)) {
                return "";
            }
            val shortLength = minOf(first.length, second.length)
            for (j in 0..<shortLength) {
                if (first[j] != second[j]) {
                    graph[first[j]]!!.add(second[j])
                    inDegree[second[j]] = inDegree[second[j]]!! + 1
                    break
                }
            }
        }

        val sb = StringBuilder()
        val queue = java.util.ArrayDeque<Char>()

        inDegree.forEach { (ch, ind) -> if (ind == 0) queue.offerLast(ch) }

        while (queue.isNotEmpty()) {
            val from = queue.removeFirst()
            sb.append(from)

            for (to in graph[from]!!) {
                inDegree[to] = inDegree[to]!! - 1
                if (inDegree[to] == 0) {
                    queue.offerLast(to)
                }
            }
        }

        return if (sb.length != inDegree.size) "" else sb.toString()
    }
}