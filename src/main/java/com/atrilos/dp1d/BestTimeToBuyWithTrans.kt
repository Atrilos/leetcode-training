package com.atrilos.dp1d

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 714
 */

fun maxProfit(prices: IntArray, fee: Int): Int {
    var free = 0
    var hold = -prices[0]

    for (i in 1..prices.lastIndex) {
        val tmp = free
        free = Math.max(free, hold + prices[i] - fee)
        hold = Math.max(hold, tmp - prices[i])
    }

    return Math.max(free, hold)
}