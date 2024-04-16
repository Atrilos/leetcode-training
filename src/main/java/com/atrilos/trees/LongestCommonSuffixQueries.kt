package com.atrilos.trees

/**
 * [3093](https://leetcode.com/problems/longest-common-suffix-queries/description/)
 */
class LongestCommonSuffixQueries {
    private class TrieNode {
        val children = Array<TrieNode?>(26) { null }
        var shortestLength = Int.MAX_VALUE
        var minIndex = Int.MAX_VALUE
    }

    private class Trie {
        val root = TrieNode()

        fun insert(s: String, index: Int) {
            var current = root
            val len = s.length

            if (len < current.shortestLength) {
                current.shortestLength = len
                current.minIndex = index
            } else if (len == current.shortestLength) {
                current.minIndex = minOf(current.minIndex, index)
            }

            for (i in s.indices.reversed()) {
                val j = s[i] - 'a'
                current.children[j] = (current.children[j] ?: TrieNode())
                current.children[j]?.let { current = it }
                if (len < current.shortestLength) {
                    current.shortestLength = len
                    current.minIndex = index
                } else if (len == current.shortestLength) {
                    current.minIndex = minOf(current.minIndex, index)
                }
            }
        }

        fun getShortestIndex(s: String): Int {
            var current = root
            var res = current.minIndex

            for (i in s.indices.reversed()) {
                val j = s[i] - 'a'
                current = current.children[j] ?: return res
                res = current.minIndex
            }

            return res
        }
    }

    fun stringIndices(wordsContainer: Array<String>, wordsQuery: Array<String>): IntArray {
        val trie = Trie()
        val res = IntArray(wordsQuery.size)

        wordsContainer.forEachIndexed { index, word -> trie.insert(word, index) }

        for (i in wordsQuery.indices) {
            res[i] = trie.getShortestIndex(wordsQuery[i])
        }

        return res
    }
}