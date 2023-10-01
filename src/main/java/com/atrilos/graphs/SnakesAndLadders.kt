package com.atrilos.graphs

import java.util.*

/**
 * [909](https://leetcode.com/problems/snakes-and-ladders/description/)
 */
fun snakesAndLadders(board: Array<IntArray>): Int {
    val n = board.size
    fun getNext(num: Int): Int {
        val row = n - 1 - (num - 1) / n
        val col = if (n % 2 == row % 2) n - 1 - (num - 1) % n else (num - 1) % n
        return board[row][col]
    }

    val queue = LinkedList<IntArray>()
    val seen = mutableSetOf<Int>()
    queue.add(intArrayOf(1, 0))
    seen.add(1)
    while (!queue.isEmpty()) {
        val (curr, steps) = queue.poll()
        if (curr == n * n) return steps
        var bigRoll = true
        for (square in minOf(curr + 6, n * n) downTo curr + 1) {
            val next = getNext(square)
            if (next == -1) {
                if (square in seen) continue
                seen.add(square)
                if (bigRoll) {
                    bigRoll = false
                    queue.add(intArrayOf(square, steps + 1))
                }
            } else {
                if (next in seen) continue
                seen.add(next)
                queue.add(intArrayOf(next, steps + 1))
            }
        }
    }
    return -1
}