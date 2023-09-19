package com.atrilos.mathGeometry

/**
 * [1359](https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/)
 */
fun countOrders(n: Int): Int {
    val mod = 1_000_000_007
    var res: Long = 1

    for (i in 2..n) {
        res = (res * i * (2 * i - 1))
        res %= mod
    }

    return res.toInt()
}

fun main() {
    countOrders(10)
}
