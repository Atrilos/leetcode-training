package com.atrilos.systemDesign

import com.atrilos.trees.TreeNode

/**
 * [173](https://leetcode.com/problems/binary-search-tree-iterator/)
 */
class BSTIterator(val root: TreeNode?) {

    val stack = java.util.ArrayDeque<TreeNode>()

    init {
        pushLeft(root)
    }

    private fun pushLeft(node: TreeNode?) {
        var n = node
        while (n != null) {
            stack.push(n)
            n = n.left
        }
    }

    fun next(): Int {
        val n = stack.pop()
        pushLeft(n.right)
        return n.`val`
    }

    fun hasNext(): Boolean = stack.isNotEmpty()
}