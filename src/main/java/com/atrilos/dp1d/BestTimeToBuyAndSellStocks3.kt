package com.atrilos.dp1d

/**
 * [123](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/)
 */
class BestTimeToBuyAndSellStocks3 {
    fun maxProfitOnePass(prices: IntArray): Int {
        var t1Cost = Int.MAX_VALUE
        var t2Cost = Int.MAX_VALUE
        var t1Profit = 0
        var t2Profit = 0

        for (price in prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = minOf(t1Cost, price)
            t1Profit = maxOf(t1Profit, (price - t1Cost))

            // reinvest the gained profit in the second transaction
            t2Cost = minOf(t2Cost, (price - t1Profit))
            t2Profit = maxOf(t2Profit, (price - t2Cost))
        }

        return t2Profit
    }

    fun maxProfitDP(prices: IntArray): Int {
        val lr = IntArray(prices.size)
        var min = prices[0]

        for (i in 1..prices.lastIndex) {
            val price = prices[i]
            min = minOf(min, price)
            lr[i] = maxOf(lr[i - 1], price - min)
        }

        var ans = lr.last()
        var max = Int.MIN_VALUE
        var maxProfit = 0

        for (i in prices.lastIndex downTo 1) {
            max = maxOf(max, prices[i])
            maxProfit = maxOf(maxProfit, max - prices[i])
            ans = maxOf(ans, maxProfit + lr[i - 1])
        }

        return ans
    }
}