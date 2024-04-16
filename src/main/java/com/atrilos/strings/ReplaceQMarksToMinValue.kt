package com.atrilos.strings

/**
 * [3081](https://leetcode.com/problems/replace-question-marks-in-string-to-minimize-its-value/description/)
 */
class ReplaceQMarksToMinValue {
    fun minimizeStringValue(s: String): String {
        val sb = StringBuilder(s)
        val set = sortedSetOf<Pair<Char, Int>>(compareBy({ it.second }, { it.first }))
        val freq = IntArray(26)

        ('a'..'z').forEach {
            set += it to 0
        }

        var questionCount = 0

        sb.forEach {
            if (it != '?') {
                set.remove(it to freq[it - 'a'])
                freq[it - 'a']++
                set.add(it to freq[it - 'a'])
            } else {
                questionCount++
            }
        }

        val pq = java.util.PriorityQueue<Char>()

        repeat(questionCount) {
            val (k, v) = set.first()
            set.remove(k to v)
            freq[k - 'a']++
            set.add(k to v + 1)
            pq.offer(k)
        }

        for (i in 0..sb.lastIndex) {
            if (sb[i] == '?') {
                sb[i] = pq.poll()
            }
        }

        return sb.toString()
    }
}