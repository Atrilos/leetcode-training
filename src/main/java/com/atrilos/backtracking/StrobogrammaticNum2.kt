package com.atrilos.backtracking

/**
 * [247](https://leetcode.com/problems/strobogrammatic-number-ii/description/)
 */
class StrobogrammaticNum2 {
    fun findStrobogrammatic(n: Int): List<String> {
        return findNums(n, n)
    }

    private fun findNums(n: Int, m: Int): List<String> {
        if (n == 0) {
            return listOf("")
        }
        if (n == 1) {
            return listOf("0", "1", "8")
        }

        val res = mutableListOf<String>()
        val mid = findNums(n - 2, m)
        for (s in mid) {
            if (n != m) {
                res.add("0" + s + "0")
            }
            res.add("1" + s + "1")
            res.add("6" + s + "9")
            res.add("8" + s + "8")
            res.add("9" + s + "6")
        }

        return res
    }
}