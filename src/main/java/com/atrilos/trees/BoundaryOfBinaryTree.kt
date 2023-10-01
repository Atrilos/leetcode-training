package com.atrilos.trees

import java.util.*

/**
 * [545](https://leetcode.com/problems/boundary-of-binary-tree/description/)
 */
object BoundaryOfBinaryTree {
    fun boundaryOfBinaryTree(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        if (root.left == null && root.right == null) return listOf(root.`val`)

        val res = mutableListOf<Int>().apply { add(root.`val`) }

        traverseLeft(root.left, res)
        traverseLeafs(root, res)
        traverseRight(root.right, res)

        return res
    }

    private fun traverseLeft(node: TreeNode?, res: MutableList<Int>) {
        var curr: TreeNode? = node

        while (curr != null) {
            if (isLeaf(curr)) break
            res += curr.`val`
            curr = if (curr.left != null) {
                curr.left
            } else {
                curr.right
            }
        }
    }

    private fun traverseLeafs(node: TreeNode?, res: MutableList<Int>) {
        if (node == null) return

        if (isLeaf(node)) {
            res += node.`val`
        }
        traverseLeafs(node.left, res)
        traverseLeafs(node.right, res)
    }

    private fun traverseRight(node: TreeNode?, res: MutableList<Int>) {
        var curr: TreeNode? = node
        val stack = LinkedList<Int>()

        while (curr != null) {
            if (isLeaf(curr)) break
            stack.push(curr.`val`)
            curr = if (curr.right != null) {
                curr.right
            } else {
                curr.left
            }
        }

        while (stack.isNotEmpty()) {
            res += stack.pop()
        }
    }

    private fun isLeaf(node: TreeNode): Boolean = node.left == null && node.right == null
}