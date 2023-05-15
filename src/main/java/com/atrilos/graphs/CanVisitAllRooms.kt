package com.atrilos.graphs

import kotlin.math.abs

fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    var cnt = 0
    val visited = BooleanArray(rooms.size)
    val deque = ArrayDeque<Int>().apply { addLast(0) }
    while (deque.isNotEmpty()) {
        val next = deque.removeFirst()
        if (!visited[next]) {
            visited[next] = true
            cnt++
            deque.addAll(rooms[next])
        }
    }

    return cnt == rooms.size
}