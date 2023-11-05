package com.atrilos.systemDesign

import java.util.*

/**
 * [642](https://leetcode.com/problems/design-search-autocomplete-system/description/)
 */
class AutocompleteSystem(sentences: Array<String>, times: IntArray) {

    data class TrieNode(val children: MutableMap<Char, TrieNode> = hashMapOf(), val sentences: MutableMap<String, Int> = hashMapOf())

    private val root = TrieNode()
    private var currNode = root

    private val dead = TrieNode()
    private val currSentence: StringBuilder = StringBuilder()
    private val comp = compareBy<String> { currNode.sentences[it] }.thenByDescending { it }

    init {
        for (i in sentences.indices) addToTrie(sentences[i], times[i])
    }

    private fun addToTrie(sentence: String, count: Int) {
        var node = root
        for (letter in sentence) {
            node = node.children.getOrPut(letter) { TrieNode() }
            node.sentences[sentence] = node.sentences.getOrDefault(sentence, 0) + count
        }
    }

    private fun endInput(): List<String> {
        addToTrie(currSentence.toString(), 1)
        currSentence.clear()
        currNode = root
        return emptyList()
    }

    fun input(c: Char): List<String> {
        val heap = PriorityQueue(comp)

        if (c == '#') return endInput()
        currSentence.append(c)
        currNode = currNode.children[c] ?: dead

        for (sentence in currNode.sentences.keys) {
            heap.add(sentence)
            if (heap.size > 3) heap.remove()
        }

        return List(heap.size) { heap.poll() }.reversed()
    }
}