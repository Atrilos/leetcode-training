package com.atrilos.strings

/**
 * [68](https://leetcode.com/problems/text-justification/)
 */
fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
    val res = mutableListOf<String>()
    var currentIndex = 0
    while (true) {
        val nextRange = getNextRange(currentIndex, words, maxWidth)
        val rangeLength = nextRange.last - nextRange.first
        if (nextRange.last == words.lastIndex) {
            res.add(words.sliceArray(nextRange).joinToString(" ").padEnd(maxWidth))
            break
        }
        if (rangeLength == 0) {
            res.add(words[nextRange.first].padEnd(maxWidth))
            currentIndex = nextRange.last + 1
            continue
        }
        val totalPadDistance = maxWidth - words.sliceArray(nextRange).fold(0) { acc, s -> acc + s.length }
        val minPadDistance = totalPadDistance / rangeLength
        var offset = totalPadDistance - (minPadDistance * rangeLength)
        val sb = StringBuilder()
        for (i in nextRange) {
            if (i == nextRange.first) {
                sb.append(words[i])
            } else {
                if (offset > 0) {
                    sb.append(" ")
                    offset--
                }
                sb.append(words[i].padStart(words[i].length + minPadDistance))
            }
        }
        res.add(sb.toString())
        currentIndex = nextRange.last + 1
    }

    return res
}

private fun getNextRange(start: Int, words: Array<String>, maxWidth: Int): IntRange {
    var end = start
    var length = 0

    while (end <= words.lastIndex && length <= maxWidth) {
        length += words[end].length + (if (end == start) 0 else 1)
        end++
    }

    return if (end == words.size && length <= maxWidth) start until end else start..end - 2
}