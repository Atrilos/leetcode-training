package com.atrilos.backtracking

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * 216
 */
fun main() {
    println(combinationSum3(3, 9).joinToString())
}
fun combinationSum3(k: Int, n: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    for (i in 1..9) {
        val list = mutableListOf(i)
        dfs(res, list, i + 1, k, n)
    }
    return res
}

private fun dfs(res: MutableList<List<Int>>, currentList: MutableList<Int>, next: Int, k: Int, n: Int): Unit {
    val sum = currentList.sum()
    var i = next
    if (currentList.size == k && sum == n) {
        res += currentList.toList()
        return
    }
    while (i < 10 && sum < n && sum + i <= n && currentList.size < k) {
        currentList += i
        dfs(res, currentList, i + 1, k, n)
        currentList.removeAt(currentList.lastIndex)
        i++
    }
}