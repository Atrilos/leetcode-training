package com.atrilos.systemDesign

import com.atrilos.trees.TreeNode


/**
 * [431](https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/description/)
 */
class CodecNaryToBinaryTree {
    // Encodes a tree to a single string.
    fun encode(root: Node?): TreeNode? {
        if (root == null) return null

        val treeNode = TreeNode(root.`val`)

        val dummyHead = TreeNode(-1)
        var curr = dummyHead
        for (child in root.children) {
            curr.right = encode(child)
            curr = curr.right!!
        }

        treeNode.left = dummyHead.right
        return treeNode
    }

    // Decodes your encoded data to tree.
    fun decode(root: TreeNode?): Node? {
        if (root == null) return null

        val node = Node(root.`val`)
        val childrenList = mutableListOf<Node?>()

        var curr = root.left

        while (curr != null) {
            childrenList.add(decode(curr))
            curr = curr.right
        }
        return node.apply { children = childrenList }
    }

    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }
}