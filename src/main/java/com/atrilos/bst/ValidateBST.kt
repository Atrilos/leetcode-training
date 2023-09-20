package com.atrilos.bst

import com.atrilos.trees.TreeNode

/**
 * [98](https://leetcode.com/problems/validate-binary-search-tree/)
 */
fun isValidBST(root: TreeNode?): Boolean {
    fun isValidBST(curr: TreeNode?, low: Long, high: Long): Boolean =
        curr == null ||
                (curr.`val` in (low + 1)..<high
                        && isValidBST(curr.left, low, curr.`val`.toLong())
                        && isValidBST(curr.right, curr.`val`.toLong(), high))

    return isValidBST(curr = root, low = Long.MIN_VALUE, high = Long.MAX_VALUE)
}