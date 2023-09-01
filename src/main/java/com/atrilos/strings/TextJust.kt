package com.atrilos.strings

/**
 * https://leetcode.com/problems/text-justification/
 * 68
 */

fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
    val result = mutableListOf<String>()
    val lineWords = mutableListOf<String>()
    var lineLength = 0

    for (word in words) {
        if (lineLength + lineWords.size + word.length <= maxWidth) {
            lineWords.add(word)
            lineLength += word.length
        } else {
            result.add(constructLine(lineWords, maxWidth, lineLength))
            lineWords.clear()
            lineWords.add(word)
            lineLength = word.length
        }
    }

    // Last line
    if (lineWords.isNotEmpty()) {
        val lastLine = lineWords.joinToString(" ")
        result.add(lastLine.padEnd(maxWidth))
    }

    return result
}

private fun constructLine(words: List<String>, maxWidth: Int, lineLength: Int): String {
    val numWords = words.size
    if (numWords == 1) {
        return words[0].padEnd(maxWidth)
    }

    val totalSpaces = maxWidth - lineLength
    val spaceSlots = numWords - 1
    val baseSpace = totalSpaces / spaceSlots
    val extraSpaceSlots = totalSpaces % spaceSlots

    val lineBuilder = StringBuilder()
    for (i in 0 until numWords - 1) {
        lineBuilder.append(words[i])
        lineBuilder.append(" ".repeat(baseSpace))
        if (i < extraSpaceSlots) {
            lineBuilder.append(" ")
        }
    }
    lineBuilder.append(words.last())

    return lineBuilder.toString()
}