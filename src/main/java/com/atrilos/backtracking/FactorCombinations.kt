package com.atrilos.backtracking

/**
 * [254](https://leetcode.com/problems/factor-combinations/description/)
 */
class FactorCombinations {
    fun getFactors(m: Int): List<List<Int>> {
        val res = ArrayList<List<Int>>()
        val path = ArrayList<Int>()

        fun backtrack(start: Int, n: Int) {
            if (n <= 1) {
                if (path.size > 1) {
                    res.add(path.toList())
                }
                return
            }

            for (i in start..n) {
                if (n % i == 0) {
                    path.add(i)
                    backtrack(i, n / i)
                    path.removeLast()
                }
            }
        }

        backtrack(2, m)
        return res
    }

}

fun main() {
    println(FactorCombinations().getFactors(100))
}