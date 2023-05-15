package com.atrilos.stack

import java.util.ArrayDeque

fun predictPartyVictory(senate: String): String {
    var currentSide: Char = senate[0]
    var banCount = 0
    val queue = ArrayDeque(senate.toCharArray().toList())
    var radiantCount = queue.count { it == 'R' }
    var direCount = senate.length - radiantCount

    while (queue.isNotEmpty() && radiantCount != 0 && direCount != 0) {
        val next = queue.removeFirst()

        if (banCount == 0) {
            queue.addLast(next)
            currentSide = next
            banCount++
        } else if (currentSide == next) {
            queue.addLast(next)
            banCount++
        } else {
            banCount--
            if (currentSide == 'R') direCount--
            else radiantCount--
        }
    }

    return if (currentSide == 'R') "Radiant" else "Dire"
}