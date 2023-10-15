package com.atrilos.bst

import com.atrilos.trees.TreeNode

/**
 * [1214](https://leetcode.com/problems/two-sum-bsts/description/)
 */
object TwoSumBST {
    fun twoSumBSTs(root1: TreeNode?, root2: TreeNode?, target: Int): Boolean {
        if (root1 == null || root2 == null) return false
        val stack1 = java.util.ArrayDeque<TreeNode>()
        val stack2 = java.util.ArrayDeque<TreeNode>()
        var p1 = root1
        var p2 = root2
        while (true) {
            while (p1 != null) {
                stack1.offerFirst(p1)
                p1 = p1.left
            }
            while (p2 != null) {
                stack2.offerFirst(p2)
                p2 = p2.right
            }

            if (stack1.isEmpty() || stack2.isEmpty()) break

            val leftVal = stack1.peekFirst().`val`
            val rightVal = stack2.peekFirst().`val`

            if (leftVal + rightVal == target) {
                return true
            } else if (leftVal + rightVal < target) {
                p1 = stack1.removeFirst().right
            } else {
                p2 = stack2.removeFirst().left
            }
        }

        return false
    }
}