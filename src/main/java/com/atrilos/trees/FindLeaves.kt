package com.atrilos.trees

/**
 * [366](https://leetcode.com/problems/find-leaves-of-binary-tree/description/)
 */
object FindLeaves {

    val map = mutableMapOf<Int, MutableList<Int>>()

    fun findLeaves(root: TreeNode?): List<List<Int>> {
        getLevel(root)
        return map.values.toList()
    }

    private fun getLevel(node: TreeNode?): Int {
        if (node == null) return 0

        val left = getLevel(node.left)
        val right = getLevel(node.right)
        val level = maxOf(left, right) + 1
        map.getOrPut(level) { mutableListOf() }.add(node.`val`)

        return level
    }
}