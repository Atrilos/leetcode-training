package com.atrilos.graphs


/**
 * [317](https://leetcode.com/problems/shortest-distance-from-all-buildings/)
 */
class ShortestDistanceFromAllBuildings {

    private val dirs = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
    fun shortestDistance(grid: Array<IntArray>): Int {
        // Total Matrix to store total distance sum for each empty cell.
        val total = Array(grid.size) { IntArray(grid[0].size) }
        var emptyLandValue = 0
        var minDist = Int.MAX_VALUE
        for (i in grid.indices) {
            for (j in grid[0].indices) {

                // Start a BFS from each house.
                if (grid[i][j] == 1) {
                    // Update min distance for each found house
                    minDist = Int.MAX_VALUE

                    // Use a queue to perform a BFS, starting from the cell at (r, c).
                    val q = java.util.ArrayDeque<Pair<Int, Int>>()
                    q.offerLast(i to j)
                    var steps = 0
                    while (!q.isEmpty()) {
                        steps++
                        repeat(q.size) {
                            val curr = q.removeFirst()

                            for (dir in dirs) {
                                val nextRow = curr.first + dir.first
                                val nextCol = curr.second + dir.second

                                // For each cell with the value equal to empty land value
                                // add distance and decrement the cell value by 1.
                                if (nextRow in grid.indices && nextCol in grid[0].indices && grid[nextRow][nextCol] == emptyLandValue) {

                                    grid[nextRow][nextCol]--
                                    total[nextRow][nextCol] += steps
                                    q.offerLast(nextRow to nextCol)
                                    minDist = minOf(minDist, total[nextRow][nextCol])
                                }
                            }
                        }
                    }

                    // Decrement empty land value to be searched in next iteration.
                    emptyLandValue--
                }
            }
        }
        return if (minDist == Int.MAX_VALUE) -1 else minDist
    }
}