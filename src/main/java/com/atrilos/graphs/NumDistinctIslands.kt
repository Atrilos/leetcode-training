package com.atrilos.graphs


/**
 * [694](https://leetcode.com/problems/number-of-distinct-islands/description/)
 */
class NumDistinctIslands {

    private val dirs = listOf((0 to 1) to 'R', (0 to -1) to 'L', (1 to 0) to 'D', (-1 to 0) to 'U')
    private lateinit var unique: MutableSet<String>
    private lateinit var currentIsland: StringBuilder

    fun numDistinctIslands(grid: Array<IntArray>): Int {
        unique = mutableSetOf()
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) {
                    currentIsland = StringBuilder()
                    mapIsland(grid, i, j, '0')
                    if (currentIsland.isNotEmpty()) {
                        unique += currentIsland.toString()
                    }
                }
            }
        }

        return unique.size
    }

    private fun mapIsland(
        grid: Array<IntArray>,
        i: Int,
        j: Int,
        dir: Char
    ) {
        if (i !in grid.indices ||
            j !in grid[0].indices ||
            grid[i][j] != 1) return

        grid[i][j] = -1
        currentIsland.append(dir)

        dirs.forEach { (it, dir) ->
            mapIsland(grid, i + it.first, j + it.second, dir)
        }

        currentIsland.append('0')
    }
}

/**
 * [711](https://leetcode.com/problems/number-of-distinct-islands-ii/description/)
 */
class NumDistinctIslands2 {
    private val dirs = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun numDistinctIslands2(grid: Array<IntArray>): Int {
        val allDistinctIslands = mutableSetOf<Map<Int, Int>>()
        val rows = grid.size
        val cols = grid[0].size
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (grid[r][c] == 1) {
                    val positions = mutableListOf<Pair<Int, Int>>()
                    getIsland(grid, r, c, positions)
                    val allDistanceCountMap = mutableMapOf<Int, Int>()
                    for (i in positions.indices) {
                        for (j in i + 1 until positions.size) {
                            val dist =
                                (positions[i].first - positions[j].first).pow2() +
                                        (positions[i].second - positions[j].second).pow2()
                            allDistanceCountMap.merge(dist, 1, Int::plus)
                        }
                    }
                    allDistinctIslands.add(allDistanceCountMap)
                }
            }
        }
        return allDistinctIslands.size
    }

    private fun getIsland(mat: Array<IntArray>, i: Int, j: Int, positions: MutableList<Pair<Int, Int>>) {
        if (i !in mat.indices || j !in mat[0].indices || mat[i][j] != 1) return
        positions.add(i to j)
        mat[i][j] = -1
        for (dir in dirs) {
            getIsland(mat, i + dir.first, j + dir.second, positions)
        }
    }

    private fun Int.pow2(): Int = this * this
}
