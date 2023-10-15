package com.atrilos.backtracking

/**
 * https://leetcode.com/problems/combinations/
 * 77
 */
fun combine(n: Int, k: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    dfs(1, n, res, mutableListOf(), k)
    return res
}

private fun dfs(from: Int, to: Int, acc: MutableList<List<Int>>, cur: MutableList<Int>, k: Int) {
    if (k == 0) {
        acc.add(cur.toList())
        return
    }

    for (i in from..to - k + 1) {
        cur.add(i)
        dfs(i + 1, to, acc, cur, k - 1)
        cur.removeAt(cur.lastIndex)
    }
}