package com.atrilos.graphs

/**
 * [127](https://leetcode.com/problems/word-ladder/description/)
 */
fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    if (endWord !in wordList) return 0

    val wordSet = wordList.toHashSet()
    var visitedBegin = mutableSetOf<String>()
    var visitedEnd = mutableSetOf<String>()

    visitedBegin.add(beginWord)
    visitedEnd.add(endWord)

    var step = 1

    while (visitedBegin.isNotEmpty() && visitedEnd.isNotEmpty()) {
        val nextVisited = mutableSetOf<String>()

        for (word in visitedBegin) {
            val wordArr = word.toCharArray()

            for (i in word.indices) {
                val originalChar = wordArr[i]

                for (ch in 'a'..'z') {
                    if (ch == originalChar) continue

                    wordArr[i] = ch
                    val transformedWord = String(wordArr)

                    if (transformedWord in visitedEnd) {
                        return step + 1
                    }

                    if (transformedWord in wordSet) {
                        nextVisited.add(transformedWord)
                        wordSet.remove(transformedWord)
                    }
                }

                wordArr[i] = originalChar
            }
        }

        step++
        visitedBegin.clear()
        visitedBegin.addAll(nextVisited)

        if (visitedBegin.size > visitedEnd.size) {
            visitedBegin = visitedEnd.also { visitedEnd = visitedBegin }
        }
    }

    return 0
}