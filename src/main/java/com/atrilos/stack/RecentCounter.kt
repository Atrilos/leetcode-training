package com.atrilos.stack

import java.util.ArrayDeque
import java.util.Deque

class RecentCounter(private var counter: Deque<Int> = ArrayDeque()) {

    fun ping(t: Int): Int {
        counter.addLast(t)
        while (counter.isNotEmpty() && counter.peekFirst() < t - 3000) {
            counter.removeFirst()
        }
        return counter.size
    }

}
