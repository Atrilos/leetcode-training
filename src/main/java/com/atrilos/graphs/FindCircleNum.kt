package com.atrilos.graphs

fun findCircleNum(isConnected: Array<IntArray>): Int {
    val visited = BooleanArray(isConnected.size)
    var cnt = 0

    for (i in isConnected.indices) {
        if (!visited[i]) {
            val deque = ArrayDeque<Int>().apply { addLast(i) }
            cnt++
            while (deque.isNotEmpty()) {
                val now = deque.removeFirst()
                visited[now] = true
                isConnected[now]
                    .mapIndexed { index, item -> if (item == 1) index else -1 }
                    .filter { it != -1 && !visited[it] }
                    .forEach { deque.addLast(it) }
            }
        }
    }

    return cnt
}