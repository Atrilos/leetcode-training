package com.atrilos.graphs

/**
 * [2976](https://leetcode.com/problems/minimum-cost-to-convert-string-i/description/)
 */
class MinCostToConvertString {
    fun minimumCost(source: String, target: String, original: CharArray, changed: CharArray, cost: IntArray): Long {
        val n = original.size
        val dp = Array(26) { LongArray(26) { Long.MAX_VALUE } }

        for (i in 0 until 26) dp[i][i] = 0

        for (i in 0 until n) {
            val o = original[i] - 'a'
            val c = changed[i] - 'a'
            dp[o][c] = minOf(dp[o][c], cost[i].toLong())
        }

        for (k in 0 until 26) {
            for (i in 0 until 26) {
                for (j in 0 until 26) {
                    if (dp[i][k] < Long.MAX_VALUE && dp[k][j] < Long.MAX_VALUE) {
                        dp[i][j] = minOf(dp[i][j], dp[i][k] + dp[k][j])
                    }
                }
            }
        }

        var totalCost = 0L

        for (i in source.indices) {
            val s = source[i] - 'a'
            val t = target[i] - 'a'
            if (dp[s][t] == Long.MAX_VALUE) return -1
            totalCost += dp[s][t]
        }

        return totalCost
    }
}

/**
 * [2977](https://leetcode.com/problems/minimum-cost-to-convert-string-ii/description/)
 */
class MinCostToConvertString2 {
    fun minimumCost(
        source: String,
        target: String,
        original: Array<String>,
        changed: Array<String>,
        cost: IntArray
    ): Long {
        val index = HashMap<String, Int>()

        for (o in original) {
            if (o !in index) {
                index[o] = index.size
            }
        }
        for (c in changed) {
            if (c !in index) {
                index[c] = index.size
            }
        }

        val dis = Array(index.size) { LongArray(index.size) { Long.MAX_VALUE } }
        for (i in dis.indices) {
            dis[i][i] = 0
        }
        for (i in cost.indices) {
            dis[index[original[i]]!!][index[changed[i]]!!] =
                minOf(dis[index[original[i]]!!][index[changed[i]]!!], cost[i].toLong())
        }
        for (k in dis.indices) {
            for (i in dis.indices) {
                if (dis[i][k] < Long.MAX_VALUE) {
                    for (j in dis.indices) {
                        if (dis[k][j] < Long.MAX_VALUE) {
                            dis[i][j] = minOf(dis[i][j], dis[i][k] + dis[k][j])
                        }
                    }
                }
            }
        }

        val set = HashSet<Int>()
        for (o in original) {
            set.add(o.length)
        }

        val dp = LongArray(target.length + 1) { Long.MAX_VALUE }
        dp[0] = 0L

        for (i in target.indices) {
            if (dp[i] == Long.MAX_VALUE) {
                continue
            }
            if (target[i] == source[i]) {
                dp[i + 1] = minOf(dp[i + 1], dp[i])
            }

            for (t in set) {
                if (i + t >= dp.size) {
                    continue
                }
                val c1 = index.getOrDefault(source.substring(i, i + t), -1)
                val c2 = index.getOrDefault(target.substring(i, i + t), -1)
                if (c1 >= 0 && c2 >= 0 && dis[c1][c2] < Long.MAX_VALUE) {
                    dp[i + t] = minOf(dp[i + t], dp[i] + dis[c1][c2])
                }
            }
        }
        return if (dp.last() == Long.MAX_VALUE) -1L else dp.last()
    }
}