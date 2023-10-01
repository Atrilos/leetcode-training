package com.atrilos.sets

/**
 * [734](https://leetcode.com/problems/sentence-similarity/description/)
 */
fun areSentencesSimilar(sentence1: Array<String>, sentence2: Array<String>, similarPairs: List<List<String>>): Boolean {
    if (sentence1.size != sentence2.size) return false
    val dict = mutableMapOf<String, MutableSet<String>>()
    similarPairs.forEach {
        dict.getOrPut(it[0]) { mutableSetOf() }.add(it[1])
        dict.getOrPut(it[1]) { mutableSetOf() }.add(it[0])
    }
    for (i in sentence1.indices) {
        if (sentence1[i] != sentence2[i] && (!dict.containsKey(sentence1[i]) || dict[sentence1[i]]?.contains(sentence2[i]) == false)) {
            return false
        }
    }
    return true
}