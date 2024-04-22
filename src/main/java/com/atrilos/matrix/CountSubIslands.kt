package com.atrilos.matrix

/**
 * [1905](https://leetcode.com/problems/count-sub-islands/description/)
 */
class CountSubIslands {
    val dirs = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

    fun countSubIslands(grid1: Array<IntArray>, grid2: Array<IntArray>): Int {
        var count = 0

        for (i in grid2.indices) {
            for (j in grid2[i].indices) {
                if (grid2[i][j] == 1 && bfs(grid1, grid2, i, j)) {
                    count++
                }
            }
        }

        return count
    }

    private fun bfs(grid1: Array<IntArray>, grid2: Array<IntArray>, r: Int, c: Int): Boolean {
        val queue = java.util.ArrayDeque<Pair<Int, Int>>()
        var subIsland = true
        queue.add(r to c)

        grid2[r][c] = 0

        while (queue.isNotEmpty()) {
            val (i, j) = queue.removeLast()
            if (grid1[i][j] == 0) {
                subIsland = false
            }
            for ((ii, jj) in dirs) {
                val (di, dj) = i + ii to j + jj
                if (di !in grid2.indices || dj !in grid2[0].indices || grid2[di][dj] == 0) {
                    continue
                }
                grid2[di][dj] = 0
                queue.addFirst(di to dj)
            }
        }

        return subIsland
    }
}