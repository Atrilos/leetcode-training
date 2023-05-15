package com.atrilos.bst

import com.atrilos.trees.TreeNode


fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
    return when {
        root == null -> null
        key < root.`val` -> root.apply { left = deleteNode(left, key) }
        key > root.`val` -> root.apply { right = deleteNode(right, key) }
        root.left == null -> root.right
        root.right == null -> root.left
        else -> {
            root.apply {
                `val` = minInBST(right!!)
                right = deleteNode(right, `val`)
            }
        }
    }
}

fun minInBST(node: TreeNode): Int {
    var current: TreeNode? = node
    var res = node.`val`

    while (current?.left != null) {
        res = current.left!!.`val`
        current = current.left
    }

    return res
}
