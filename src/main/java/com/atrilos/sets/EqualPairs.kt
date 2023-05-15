package com.atrilos.sets

fun equalPairs(grid: Array<IntArray>): Int {
    var res = 0
    val columnHashes = mutableMapOf<List<Int>, Int>()

    for (i in grid.indices) {
        val column = IntArray(grid.size)
        for (j in grid.indices) {
            column[j] = grid[j][i]
        }
        columnHashes.merge(column.toList(), 1, Int::plus)
    }

    for (line in grid) {
        val lineHash = line.toList()
        res += columnHashes.getOrDefault(lineHash, 0)
    }

    return res
}