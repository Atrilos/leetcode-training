package com.atrilos.trees

/**
 * [222](https://leetcode.com/problems/count-complete-tree-nodes/description/)
 */
class CountComplete {
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0
        val l = countLeftHeight(root, 1)
        val r = countRightHeight(root, 1)
        return if (l == r) {
            pow2(l)
        } else {
            countNodes(root.left) + countNodes(root.right) + 1
        }
    }

    private fun countLeftHeight(node: TreeNode?, h: Int): Int {
        node ?: return h - 1
        return countLeftHeight(node.left, h + 1)

    }

    private fun countRightHeight(node: TreeNode?, h: Int): Int {
        node ?: return h - 1
        return countRightHeight(node.right, h + 1)
    }

    private fun pow2(exp: Int): Int {
        var n = exp
        var res = 1
        while (n != 0) {
            res *= 2
            n--
        }
        return res - 1
    }
}