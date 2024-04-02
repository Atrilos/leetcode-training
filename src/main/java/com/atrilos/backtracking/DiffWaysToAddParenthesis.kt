package com.atrilos.backtracking

/**
 * [241](https://leetcode.com/problems/different-ways-to-add-parentheses/description/)
 */
class DiffWaysToAddParenthesis {
    private val memo = mutableMapOf<String, List<Int>>()

    fun diffWaysToCompute(expression: String): List<Int> {
        if (expression in memo) {
            return memo[expression]!!
        }

        val list = mutableListOf<Int>()
        for (i in expression.indices) {
            if (expression[i].isDigit()) continue

            val left = diffWaysToCompute(expression.substring(0, i))
            val right = diffWaysToCompute(expression.substring(i+1))

            for (l in left) {
                for (r in right) {
                    when (expression[i]) {
                        '+' -> list.add(l + r)
                        '-' -> list.add(l - r)
                        '*' -> list.add(l * r)
                    }
                }
            }
        }

        if (list.isEmpty()) {
            list.add(expression.toInt())
        }

        memo[expression] = list

        return list
    }
}