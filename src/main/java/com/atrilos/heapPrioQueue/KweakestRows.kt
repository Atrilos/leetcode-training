package com.atrilos.heapPrioQueue

/**
 * [1337](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/description/)
 */
fun kWeakestRows(mat: Array<IntArray>, k: Int): IntArray {
    val pq = java.util.PriorityQueue<Pair<Int, Int>>(compareBy({ it.second }, { it.first }))
    for (i in mat.indices) {
        var cnt = 0
        for (j in mat[i].indices) {
            if (mat[i][j] == 1) cnt++
        }
        pq += i to cnt
    }

    return IntArray(k) { pq.poll().first }
}