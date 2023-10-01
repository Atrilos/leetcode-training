package com.atrilos.trees

/**
 * [314](https://leetcode.com/problems/binary-tree-vertical-order-traversal/)
 */
object VerticalOrderTraversal {

    val map = mutableMapOf<Int, MutableList<Int>>()

    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        return getColumn(root)
    }

    private fun getColumn(root: TreeNode): List<List<Int>> {
        val queue = java.util.ArrayDeque<Pair<Int, TreeNode>>().apply { add(0 to root) }
        var min = 0

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val (column, node) = queue.removeFirst()
                min = minOf(min, column)
                map.getOrPut(column) { mutableListOf() }.add(node.`val`)
                if (node.left != null) {
                    queue.offerLast(column - 1 to node.left!!)
                }
                if (node.right != null) {
                    queue.offerLast(column + 1 to node.right!!)
                }
            }
        }

        val res = mutableListOf<List<Int>>()
        for (i in min..<min + map.size) {
            res += map[i]!!
        }

        return res
    }
}
