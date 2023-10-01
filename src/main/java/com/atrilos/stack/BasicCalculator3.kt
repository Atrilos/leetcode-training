package com.atrilos.stack

import java.util.LinkedList

/**
 * [772](https://leetcode.com/problems/basic-calculator-iii/)
 */
object BasicCalculator3 {
    fun calculate(s: String): Int {
        val rpn: List<Pair<String, Boolean>> = createRpn(s)
        val stack = LinkedList<Int>()
        for ((item, isNum) in rpn) {
            if (isNum) {
                val num = Integer.parseInt(item)
                stack.push(num)
            } else {
                when (item) {
                    "+" -> {
                        val second = stack.pop()!!
                        val first = stack.pop()!!
                        stack.push(first + second)
                    }
                    "-" -> {
                        val second = stack.pop()!!
                        val first = stack.pop()!!
                        stack.push(first - second)
                    }
                    "*" -> {
                        val second = stack.pop()!!
                        val first = stack.pop()!!
                        stack.push(first * second)
                    }
                    "/" -> {
                        val second = stack.pop()!!
                        val first = stack.pop()!!
                        stack.push(first / second)
                    }
                }
            }
        }
        return stack.pop()
    }

    private fun createRpn(s: String): List<Pair<String, Boolean>> {
        val stack = LinkedList<Char>()
        val res = mutableListOf<Pair<String, Boolean>>()
        var i = 0
        val priority = mutableMapOf('-' to 1, '+' to 1, '/' to 2, '*' to 2, '(' to -1)

        fun parseNum(): String {
            val start = i

            while (i <= s.lastIndex && s[i] in '0'..'9') {
                i++
            }

            return s.substring(start, i)
        }

        while (i <= s.lastIndex) {
            when (s[i]) {
                in '0'..'9' -> res += parseNum() to true
                '(' -> stack.push(s[i++])
                ')' -> {
                    while (stack.isNotEmpty() && stack.peek() != '(') {
                        res += stack.pop().toString() to false
                    }
                    stack.pop()
                    i++
                }
                else -> {
                    val currentPriority = priority[s[i]]!!
                    while (stack.isNotEmpty() && priority[stack.peek()]!! >= currentPriority) {
                        res += stack.pop().toString() to false
                    }
                    stack.push(s[i++])
                }
            }
        }
        while (stack.isNotEmpty()) res += stack.pop().toString() to false
        return res
    }
}