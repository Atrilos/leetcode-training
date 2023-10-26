package com.atrilos.backtracking

import kotlin.math.pow

/**
 * [779](https://leetcode.com/problems/k-th-symbol-in-grammar/description/)
 */
class KthSymbolInGrammar {
    private fun dfs(n: Int, k: Int, rootVal: Int): Int {
        if (n == 1) {
            return rootVal
        }
        val totalNodes: Int = 2.0.pow((n - 1).toDouble()).toInt()

        // Target node will be present in the right half sub-tree of the current root node.
        return if (k > totalNodes / 2) {
            dfs(n - 1, k - totalNodes / 2, 1 - rootVal)
        } else {
            dfs(n - 1, k, rootVal)
        }
    }

    fun kthGrammar(n: Int, k: Int): Int {
        return dfs(n, k, 0)
    }
}