package com.atrilos.stack

import java.util.*

/**
 * [439](https://leetcode.com/problems/ternary-expression-parser/description/)
 */
fun parseTernary(expression: String): String {

    // Initialize a stack
    val stack = LinkedList<Char>()
    var i = expression.lastIndex

    // Traverse the expression from right to left
    while (i >= 0) {

        // Current character
        val curr = expression[i]

        // Push every T, F, and digit on the stack
        if (curr in '0'..'9' || curr == 'T' || curr == 'F') {
            stack.push(curr)
        }

        // As soon as we encounter ?,
        // replace top two elements of the stack with one
        else if (curr == '?') {
            val onTrue = stack.pop()
            val onFalse = stack.pop()
            stack.push(if (expression[i - 1] == 'T') onTrue else onFalse)

            // Decrement i by 1 as we have already used
            // Previous Boolean character
            i -= 1
        }

        // Go to the previous character
        i -= 1
    }

    // Return the final character
    return stack.pop().toString()
}