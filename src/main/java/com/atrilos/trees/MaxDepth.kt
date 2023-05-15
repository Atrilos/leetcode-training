package com.atrilos.trees

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    return maxOf(maxDepth(root.right), maxDepth(root.left)) + 1
}