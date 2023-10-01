package com.atrilos.intervals

/**
 * [616](https://leetcode.com/problems/add-bold-tag-in-string/description/)
 */
fun addBoldTag(s: String, words: Array<String>): String {
    val bold = BooleanArray(s.length)

    for (word in words) {
        var start = s.indexOf(word)
        while (start != -1) {
            (start..<start + word.length).forEach { bold[it] = true }
            start = s.indexOf(word, start + 1)
        }
    }

    val sb = StringBuilder()
    val openingTag = "<b>"
    val closingTag = "</b>"

    for ((i, c) in s.withIndex()) {
        if (bold[i] && (i == 0 || !bold[i - 1])) {
            sb.append(openingTag)
        } else if (!bold[i] && i != 0 && bold[i - 1]) {
            sb.append(closingTag)
        }
        sb.append(c)
    }

    if (bold[s.lastIndex]) sb.append(closingTag)
    return sb.toString()
}