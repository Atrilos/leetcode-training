package com.atrilos.trees

fun goodNodes(root: TreeNode): Int {
    return dfs(root, root.`val`)
}

private fun dfs(node: TreeNode?, max: Int): Int {
    return when {
        node == null -> 0
        node.`val` == max -> dfs(node.right, max) + dfs(node.left, max) + 1
        node.`val` > max -> dfs(node.right, node.`val`) + dfs(node.left, node.`val`) + 1
        else -> dfs(node.right, max) + dfs(node.left, max)
    }
}