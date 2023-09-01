package com.atrilos.stack

import java.util.ArrayDeque

/**
 * https://leetcode.com/problems/online-stock-span/
 * 901
 */
class StockSpanner {

    private val deque = ArrayDeque<Pair<Int, Int>>() // (Price, Answer)

    fun next(price: Int): Int {
        var ans = 1

        while (deque.isNotEmpty() && deque.peekFirst().first <= price) ans += deque.removeFirst().second

        deque.offerFirst(price to ans)

        return ans
    }
}