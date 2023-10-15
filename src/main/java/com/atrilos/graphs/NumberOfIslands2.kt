package com.atrilos.graphs

/**
 * [305](https://leetcode.com/problems/number-of-islands-ii/description/)
 */
class NumberOfIslands2 {

    class DSU(val n: Int) {
        private val parent = IntArray(n) { it }
        private val size = IntArray(n) { 1 }
        var components = 0

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(x: Int, y: Int) {
            val (xP, yP) = find(x) to find(y)
            if (xP == yP) return
            if (size[xP] > size[yP]) {
                size[xP] += size[yP]
                parent[yP] = xP
            } else {
                size[yP] += size[xP]
                parent[xP] = yP
            }
            components-- // since two islands got merged, we need to decrease component count by 1
        }
    }

    fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {
        val dsu = DSU(m * n)
        val grid = Array(m) { IntArray(n) }
        val ans: MutableList<Int> = ArrayList()
        val dirs = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

        for ((i, j) in positions) {
            if (grid[i][j] == 1) { // important base-case, if the node is already a land
                ans.add(dsu.components)
                continue
            }
            grid[i][j] = 1
            dsu.components++ // let's increase number of components == means, number of islands
            // unite 4-d neighbors if they are land.
            dirs.filter {
                i + it.first in grid.indices
                        && j + it.second in grid[i].indices
                        && grid[i + it.first][j + it.second] == 1
            }.forEach {
                dsu.union(encode(i, j, n), encode(i + it.first, j + it.second, n))
            }
            ans.add(dsu.components)
        }
        return ans
    }

    private fun encode(i: Int, j: Int, n: Int): Int { // usual way of encoding 2d to 1d
        return i * n + j
    }
}