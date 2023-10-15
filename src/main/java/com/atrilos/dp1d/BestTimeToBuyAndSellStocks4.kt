package com.atrilos.dp1d

/**
 * [188](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/)
 */
class BestTimeToBuyAndSellStocks4 {
    fun maxProfit(k: Int, prices: IntArray): Int {
        if (k >= prices.size / 2) { // if k >= n/2, then you can make maximum number of transactions
            var profit = 0
            for (i in 1..prices.lastIndex) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1]
                }
            }
            return profit
        }
        val buy = IntArray(k + 1) { Int.MIN_VALUE }
        val sell = IntArray(k + 1)

        for (price in prices) {
            for (i in 1..k) {
                buy[i] = maxOf(buy[i], sell[i - 1] - price)
                sell[i] = maxOf(sell[i], buy[i] + price)
            }
        }

        return sell[k]
    }

    fun maxProfitDP(k: Int, prices: IntArray): Int {
        val n = prices.size
        val dp = Array(n + 1) { Array(k + 1) { intArrayOf(Int.MIN_VALUE, 0) } } // 0: hold, 1: nohold

        for (i in 1..n) {
            val p = prices[i - 1]
            for (j in 1..k) {
                dp[i][j][0] = maxOf(dp[i - 1][j][0], dp[i][j - 1][1] - p)
                dp[i][j][1] = maxOf(dp[i - 1][j][1], dp[i][j][0] + p)
            }
        }

        return dp[n][k][1]
    }
}