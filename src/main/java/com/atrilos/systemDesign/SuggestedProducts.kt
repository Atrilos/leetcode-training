package com.atrilos.systemDesign

import java.util.*

/**
 * https://leetcode.com/problems/search-suggestions-system/
 * 1268
 */

class SuggestedProducts {
    fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        val resList = ArrayList<List<String>>()
        val trie = Trie()

        products.forEach { trie.insert(it) }

        for (i in 1..searchWord.length) {
            resList += trie.find3StartingWith(searchWord.substring(0, i))
        }

        return resList
    }

    private class Trie {
        private val root = TrieNode()

        fun insert(word: String) {
            var current = root
            for (i in word.indices) {
                val j = word[i] - 'a'
                current.children[j] = (current.children[j] ?: TrieNode())
                current.children[j]?.let { current = it }
            }
            current.isWord = true
        }

        fun find3StartingWith(phrase: String): List<String> {
            var current = root
            val set = TreeSet<String>()

            for (i in phrase.indices) {
                val j = phrase[i] - 'a'
                current = current.children[j] ?: return emptyList()
            }

            dfs(current, set, StringBuilder(phrase))

            return if (set.size > 3) set.toList().subList(0, 3) else set.toList()
        }

        private fun dfs(root: Trie.TrieNode?, currentSet: TreeSet<String>, currentWord: StringBuilder) {
            if (root == null) {
                return
            }
            if (root.isWord) {
                currentSet += currentWord.toString()
            }

            for (i in root.children.indices) {
                val node = root.children[i]
                val ch = 'a' + i

                node?.let { dfs(it, currentSet, currentWord.append(ch)) }
                node?.let { currentWord.setLength(currentWord.length - 1) }
            }
        }

        private inner class TrieNode {
            var isWord: Boolean = false
            val children = Array<TrieNode?>(26) { null }
        }
    }
}