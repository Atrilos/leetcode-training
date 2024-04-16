package com.atrilos.dp1d

import kotlin.math.abs

/**
 * [1478](https://leetcode.com/problems/allocate-mailboxes/)
 */
class AllocateMailboxes {
    private var MAX: Int = 10000000

    fun minDistance(houses: IntArray, k: Int): Int {
        val n = houses.size
        val dp = Array(n) { IntArray(k) { -1 } }

        houses.sort()

        return solve(houses, k, 0, 0, dp)
    }

    private fun solve(houses: IntArray, k: Int, start: Int, curK: Int, dp: Array<IntArray>): Int {
        if (start == houses.size) {
            if (curK == k) {
                return 0
            }
            return MAX
        }
        if (curK == k) return MAX
        if (dp[start][curK] != -1) return dp[start][curK]

        var answer = MAX
        for (i in start..houses.lastIndex) {
            // Best way to place a mailbox between [i, pos] houses is to place at the median house
            val median = houses[(start + i) / 2]

            // Step 1: Calculate cost when we place at median house
            var cost = 0
            for (j in start..i) {
                cost += abs(median - houses[j])
            }

            // Step 2: Recursively, calculate cost of placing rest of the mailboxes at i+1 pos
            answer = minOf(answer, solve(houses, k, i + 1, curK + 1, dp) + cost)
        }

        dp[start][curK] = answer
        return answer
    }
}