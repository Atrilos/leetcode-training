package com.atrilos.systemDesign

class Trie {
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

    fun search(word: String) = searchPhrase(word, true)

    fun startsWith(prefix: String) = searchPhrase(prefix, false)

    private fun searchPhrase(phrase: String, isWord: Boolean): Boolean {
        var current = root
        for (i in phrase.indices) {
            val j = phrase[i] - 'a'
            current = current.children[j] ?: return false
        }
        return !isWord || current.isWord
    }

    private inner class TrieNode {
        var isWord: Boolean = false
        val children = Array<TrieNode?>(26) { null }
    }
}