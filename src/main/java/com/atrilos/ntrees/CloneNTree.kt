package com.atrilos.ntrees

/**
 * [1490](https://leetcode.com/problems/clone-n-ary-tree/description/)
 */
class CloneNTree {
    fun cloneTree(root: Node?): Node? =
        if (root == null) {
            null
        } else {
            Node(root.`val`).apply { children = root.children.map { cloneTree(it) } }
        }
}

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}