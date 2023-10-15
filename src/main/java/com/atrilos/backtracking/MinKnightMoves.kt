package com.atrilos.backtracking

import java.lang.IllegalArgumentException
import kotlin.math.abs

/**
 * [1197](https://leetcode.com/problems/minimum-knight-moves/description/)
 */
fun minKnightMoves(xEnd: Int, yEnd: Int): Int {
    data class State(val x: Int, val y: Int, val dist: Int)
    val dirs = listOf(-1 to 2, -2 to 1, -2 to -1, -1 to -2, 1 to 2, 2 to 1, 1 to -2, 2 to -1)

    val (xEnd, yEnd) = abs(xEnd) to abs(yEnd)

    val queue = java.util.PriorityQueue<State>(compareBy { it.dist })
    val seen = Array(303) { BooleanArray(303) }
    queue.add(State(2, 2, 0))

    while (queue.isNotEmpty()) {
        val (x, y, dist) = queue.remove()
        if (seen[x][y]) continue

        seen[x][y] = true

        if (x == xEnd && y == yEnd) return dist

        for ((xx, yy) in dirs) {
            val (newX, newY) = x + xx to y + yy
            if (newX < 0 || newY < 0) continue
            queue.add(State(newX, newY, dist + 1))
        }
    }

    throw IllegalArgumentException()
}

