package com.atrilos.stack

/**
 * [71](https://leetcode.com/problems/simplify-path/)
 */
fun simplifyPath(path: String): String {
    val deque = java.util.ArrayDeque<String>()
    val elements = path.split("/")
    for (element in elements) {
        if (element.isBlank()) continue
        when (element) {
            "." -> continue
            ".." -> if (deque.isNotEmpty()) deque.removeLast()
            else -> deque.addLast("/$element")
        }
    }

    if (deque.isEmpty()) deque.addLast("/")

    return deque.joinToString("")
}

fun main() {
    println(simplifyPath("/home//foo/"))
}