package com.atrilos.bst

import com.atrilos.trees.TreeNode

/**
 * [333](https://leetcode.com/problems/largest-bst-subtree/description/)
 */
object LargestBST {

    private data class Result(val min: Int, val max: Int, val size: Int)

    fun largestBSTSubtree(root: TreeNode?): Int {
        return postorder(root).size
    }

    private fun postorder(node: TreeNode?): Result {
        // Empty BST is size of 0
        if (node == null)
            return Result(Int.MAX_VALUE, Int.MIN_VALUE, 0)

        // LRN - postorder
        val left = postorder(node.left)
        val right = postorder(node.right)

        // Current node is greater than max in left AND smaller than min in right, it is a BST.
        // Otherwise, return [-inf, inf] so parent can't be valid BST
        return if (node.`val` > left.max && node.`val` < right.min) {
            val min = minOf(node.`val`, left.min)
            val max = maxOf(node.`val`, right.max)
            val size = left.size + right.size + 1
            Result(min, max, size)
        } else {
            val size = maxOf(left.size, right.size)
            Result(Integer.MIN_VALUE, Integer.MAX_VALUE, size)
        }
    }
}