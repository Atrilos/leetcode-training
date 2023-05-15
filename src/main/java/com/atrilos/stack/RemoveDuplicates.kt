package com.atrilos.stack

import java.util.ArrayDeque

fun removeDuplicates(s: String, k: Int): String {
    val deque: ArrayDeque<Pair<Char, Int>> = ArrayDeque()

    for (c in s) {
        if (deque.isEmpty()) {
            deque.addLast(c to 1)
        } else {
            val (prevChar, prevCnt) = deque.peekLast()
            if (prevChar == c) {
                deque.addLast(c to prevCnt + 1)
            } else {
                deque.addLast(c to 1)
            }
        }
        if (deque.peekLast().second == k) {
            repeat(k) {
                deque.removeLast()
            }
        }
    }

    return deque.map { it.first }.joinToString("")
}