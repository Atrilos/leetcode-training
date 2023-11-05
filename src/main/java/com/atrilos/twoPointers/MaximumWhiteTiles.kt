package com.atrilos.twoPointers

/**
 * [2271](https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/description/)
 */
class MaximumWhiteTiles {
    fun maximumWhiteTiles(tiles: Array<IntArray>, carpetLen: Int): Int {
        tiles.sortWith(compareBy { it[0] })
        var x = 0
        var y = 0
        var maxCount = 0
        var count = 0

        while (y < tiles.size && x <= y) {
            val start = tiles[x][0]
            val end = tiles[y][1]
            if (end - start + 1 <= carpetLen) {
                count += tiles[y][1] - tiles[y][0] + 1
                maxCount = maxOf(maxCount, count)
                y++
            } else {
                val midDist = start + carpetLen - 1
                val s = tiles[y][0]
                val e = tiles[y][1]
                if (midDist in s..e) {
                    maxCount = maxOf(maxCount, (count + midDist - s + 1))
                }
                count -= tiles[x][1] - tiles[x][0] + 1
                x++
            }
        }

        return maxCount
    }
}