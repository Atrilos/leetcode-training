package com.atrilos.trees


/**
 * [549](https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/)
 */
class LongestConsecutive2 {
    private var maxLength = 0

    fun longestConsecutive(root: TreeNode?): Int {
        longestPath(root)
        return maxLength
    }

    private fun longestPath(root: TreeNode?): IntArray {
        if (root == null) {
            return intArrayOf(0, 0)
        }
        var inr = 1
        var dcr = 1
        if (root.left != null) {
            val left = longestPath(root.left)
            if (root.`val` == root.left!!.`val` + 1) {
                dcr = left[1] + 1
            } else if (root.`val` == root.left!!.`val` - 1) {
                inr = left[0] + 1
            }
        }
        if (root.right != null) {
            val right = longestPath(root.right)
            if (root.`val` == root.right!!.`val` + 1) {
                dcr = maxOf(dcr, (right[1] + 1))
            } else if (root.`val` == root.right!!.`val` - 1) {
                inr = maxOf(inr, (right[0] + 1))
            }
        }
        maxLength = maxOf(maxLength, dcr + inr - 1)
        return intArrayOf(inr, dcr)
    }
}