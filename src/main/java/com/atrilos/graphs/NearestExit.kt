package com.atrilos.graphs

import java.util.*

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot
 * step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell
 * that is at the border of the maze. The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 */
fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
    val directions = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
    val queue = ArrayDeque<Pair<Int, Int>>()
    val rowSize = maze.size
    val colSize = maze.first().size
    val visited = Array(rowSize) { BooleanArray(colSize) }
    var steps = 0

    queue.addFirst(entrance.first() to entrance.last())
    visited[entrance.first()][entrance.last()] = true

    while (queue.isNotEmpty()) {
        steps++

        repeat(queue.size) {
            val (curRow, curCol) = queue.removeFirst()

            for (dir in directions) {
                val nextRow = curRow + dir[0]
                val nextCol = curCol + dir[1]
                if (nextRow in 0 until rowSize && nextCol in 0 until colSize
                        && !visited[nextRow][nextCol] && maze[nextRow][nextCol] == '.') {
                    if (nextRow == 0 || nextRow == rowSize - 1 || nextCol == 0 || nextCol == colSize - 1) {
                        return steps
                    }
                    queue.addLast(nextRow to nextCol)
                    visited[nextRow][nextCol] = true
                }
            }
        }
    }

    return -1
}