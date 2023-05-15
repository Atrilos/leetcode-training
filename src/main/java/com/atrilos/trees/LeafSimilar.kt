package com.atrilos.trees

fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
    return dfs(root1) == dfs(root2)
}

private fun dfs(node: TreeNode?): List<Int> {
    return when {
        node == null -> emptyList()
        node.left == null && node.right == null -> listOf(node.`val`)
        else -> dfs(node.left) + dfs(node.right)
    }
}
