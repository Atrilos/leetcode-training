package com.atrilos.graphs

/**
 * [286](https://leetcode.com/problems/walls-and-gates/)
 */
class WallsAndGatesKt {
    private val dirs = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
    fun wallsAndGates(rooms: Array<IntArray>) {
        val queue = java.util.ArrayDeque<Pair<Int, Int>>()
        for (i in rooms.indices) {
            for (j in rooms[i].indices) {
                if (rooms[i][j] == 0)
                    queue.offerLast(i to j)
            }
        }
        while (queue.isNotEmpty()) {
            val (i, j) = queue.removeFirst()

            for ((ii, jj) in dirs) {
                val r = i + ii
                val c = j + jj
                if (r !in rooms.indices || c !in rooms[0].indices || rooms[r][c] != Int.MAX_VALUE) continue
                rooms[r][c] = rooms[i][j] + 1
                queue.offerLast(r to c)
            }
        }
    }
}