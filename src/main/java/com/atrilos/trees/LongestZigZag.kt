package com.atrilos.trees

class SolutionMine {

    var max = 0
    fun longestZigZag(root: TreeNode?): Int {
        dfs(root?.left, 'l', 0)
        dfs(root?.right, 'r', 0)
        return max
    }

    private fun dfs(node: TreeNode?, direction: Char, steps: Int) {
        val oppositeDirection = if (direction == 'l') 'r' else 'l'
        when {
            node == null -> {
                max = maxOf(max, steps - 1)
            }

            direction == 'l' -> {
                dfs(node.left, direction, 1)
                dfs(node.right, oppositeDirection, steps + 1)
            }

            else -> {
                dfs(node.right, direction, 1)
                dfs(node.left, oppositeDirection, steps + 1)
            }
        }
    }

}

class SolutionShort {
    fun longestZigZag(root: TreeNode): Int = dfs(root, 0, 0)

    private fun dfs(root: TreeNode?, l: Int, r: Int): Int {
        if (root == null) return maxOf(l, r) - 1
        return maxOf(dfs(root.left, r + 1, 0), dfs(root.right, 0, l + 1))
    }
}