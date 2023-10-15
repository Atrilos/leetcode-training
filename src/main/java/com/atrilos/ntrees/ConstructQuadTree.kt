package com.atrilos.ntrees

/**
 * [427](https://leetcode.com/problems/construct-quad-tree/description/)
 */
class ConstructQuadTree {
    class Node(var `val`: Boolean, var isLeaf: Boolean) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
    }

    fun construct(grid: Array<IntArray>): Node? {
        if (grid.isEmpty()) {
            return null
        }
        return construct(grid, 0, 0, grid.size)
    }

    private fun construct(grid: Array<IntArray>, r: Int, c: Int, size: Int): Node {
        if (size == 1) {
            return Node(grid[r][c] == 1, true)
        }
        val newSize = size / 2
        val topLeft = construct(grid, r, c, newSize)
        val topRight = construct(grid, r, c + newSize, newSize)
        val bottomLeft = construct(grid, r + newSize, c, newSize)
        val bottomRight = construct(grid, r + newSize, c + newSize, newSize)
        val isLeaf = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.`val` == topRight.`val` && topLeft.`val` == bottomLeft.`val`
                && topLeft.`val` == bottomRight.`val`
        return if (isLeaf) {
            Node(grid[r][c] == 1, true)
        } else {
            Node(`val` = true, isLeaf = false).apply {
                this.topLeft = topLeft
                this.topRight = topRight
                this.bottomLeft = bottomLeft
                this.bottomRight = bottomRight
            }
        }
    }
}