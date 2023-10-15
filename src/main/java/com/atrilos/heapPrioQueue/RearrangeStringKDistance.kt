package com.atrilos.heapPrioQueue


/**
 * [358](https://leetcode.com/problems/rearrange-string-k-distance-apart/description/)
 */
class RearrangeStringKDistance {
    fun rearrangeStringGreedy(s: String, k: Int): String {
        val freqMap = mutableMapOf<Char, Int>()
        var maxFreq = 0

        for (c in s) {
            val newFreq = freqMap.getOrDefault(c, 0) + 1
            freqMap[c] = newFreq
            maxFreq = maxOf(maxFreq, newFreq)
        }

        val mostFreqChars = mutableListOf<Char>()
        val secondFreqChars = mutableListOf<Char>()
        for ((key, freq) in freqMap) {
            if (freq == maxFreq) {
                mostFreqChars += key
            } else if (freq == maxFreq - 1) {
                secondFreqChars += key
            }
        }

        val chunks = Array(maxFreq) { StringBuilder() }
        for (i in chunks.indices) {
            mostFreqChars.forEach {
                chunks[i].append(it)
            }
            if (i != chunks.lastIndex) {
                secondFreqChars.forEach {
                    chunks[i].append(it)
                }
            }
        }

        var intervalId = 0
        for ((char, freq) in freqMap) {
            if (freq == maxFreq || freq == maxFreq - 1) continue
            repeat(freq) {
                chunks[intervalId].append(char)
                intervalId = (intervalId + 1) % chunks.lastIndex
            }
        }

        for ((i, chunk) in chunks.withIndex()) {
            if (chunk.length < k && i != chunks.lastIndex) return ""
        }

        return chunks.joinToString("")
    }

    fun rearrangeStringPriorityQueue(s: String, k: Int): String {
        val res = StringBuilder()

        val freqMap = mutableMapOf<Char, Int>()
        for (c in s) {
            freqMap.merge(c, 1, Int::plus)
        }

        val available = java.util.PriorityQueue<Pair<Char, Int>>(compareByDescending { it.second })
        val used = java.util.PriorityQueue<Pair<Char, Int>>(compareBy { it.second })
        freqMap.forEach { (c, freq) -> available.add(c to freq) }

        while (res.length != s.length) {
            val index = res.length

            if (used.isNotEmpty() && index - used.peek().second >= k) {
                val (c, _) = used.remove()
                available.add(c to freqMap[c]!!)
            }

            if (available.isEmpty()) {
                return ""
            }

            val currChar = available.peek().first
            available.remove()
            res.append(currChar)

            freqMap[currChar] = freqMap[currChar]!! - 1
            if (freqMap[currChar]!! > 0) {
                used.add(currChar to index)
            }
        }

        return res.toString()
    }
}