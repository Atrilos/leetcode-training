package com.atrilos.stack


/**
 * [150](https://leetcode.com/problems/evaluate-reverse-polish-notation/)
 */
fun evalRPN(tokens: Array<String>): Int {
    val stack = java.util.ArrayDeque<Int>()

    for (token in tokens) {
        stack += when (token) {
            "+" -> stack.pop() + stack.pop()
            "*" -> stack.pop() * stack.pop()
            "-" -> stack.pop().let { stack.pop() - it }
            "/" -> stack.pop().let { stack.pop() / it }
            else -> token.toInt()
        }
    }

    return stack.pop()
}