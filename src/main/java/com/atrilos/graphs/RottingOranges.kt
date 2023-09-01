package com.atrilos.graphs

import java.util.ArrayDeque

fun main() {
    println(orangesRotting(arrayOf(intArrayOf(0))))
}
fun orangesRotting(grid: Array<IntArray>): Int {
    val directions = arrayOf(intArrayOf(0, -1), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0))
    var fresh = 0
    var steps = 0
    val rowSize = grid.size
    val colSize = grid.first().size
    val queue = ArrayDeque<Pair<Int, Int>>()

    grid.forEachIndexed { i, row ->
        row.forEachIndexed { j, it ->
            when (it) {
                1 -> fresh++
                2 -> queue.addLast(i to j)
            }
        }
    }

    while (queue.isNotEmpty() && fresh > 0) {
        steps++

        repeat(queue.size) {
            val current = queue.removeFirst()

            for (dir in directions) {
                val x = current.first + dir.first()
                val y = current.second + dir.last()

                if (x in 0 until rowSize && y in 0 until colSize && grid[x][y] == 1) {
                    grid[x][y] = 2
                    queue.addLast(x to y)
                    fresh--
                }
            }
        }
    }

    return if (fresh == 0) steps else -1
}