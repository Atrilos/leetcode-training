package com.atrilos.bit

/**
 * [691](https://leetcode.com/problems/stickers-to-spell-word/description/)
 */
class StickersToSpellWord {
    fun minStickers(stickers: Array<String>, target: String): Int {
        val startState = (1 shl target.length) - 1
        var validStickers = stickers.toSet()
        var steps = 0
        val seenStates = mutableSetOf<Int>()

        val queue = ArrayDeque<Int>()
        queue.add(startState)

        while (queue.isNotEmpty() && validStickers.isNotEmpty()) {
            val tmp = mutableSetOf<String>()
            steps++

            repeat (queue.size) {
                val state = queue.removeFirst()

                for (word in validStickers) {
                    val newState = getNewTarget(target, state, word)
                    if (seenStates.add(newState)) {
                        if (newState == 0) return steps
                        queue.add(newState)
                        tmp.add(word)
                    }
                }
            }

            validStickers = tmp
        }

        return -1
    }

    private fun getNewTarget(target: String, state: Int, sticker: String): Int {
        var res = state
        val stickerFreq = IntArray(26)
        for (c in sticker) { stickerFreq[c - 'a']++ }

        var nBit = 1
        for (element in target) {
            val idx = element - 'a'
            if ((res and nBit) == nBit && stickerFreq[idx] > 0) {
                stickerFreq[idx]--
                res = res xor nBit
            }
            nBit = nBit shl 1
        }
        return res
    }
}