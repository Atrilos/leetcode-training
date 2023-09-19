package com.atrilos.bit

/**
 * [2857](https://leetcode.com/problems/count-pairs-of-points-with-distance-k/description/)
 */
fun countPairs(c: List<List<Int>>, k: Int): Int {
    var res = 0
    val n = c.size
    val map = mutableMapOf<Pair<Int, Int>, Int>()
    for (i in n - 1 downTo 0) {
        for (j in 0..k) {
            val right = k - j
            val xorLeft = j xor c[i][0]
            val xorRight = right xor c[i][1]
            res += map[xorLeft to xorRight]?: 0
        }
        map.merge(c[i][0] to c[i][1], 1, Int::plus)
    }
    return res
}
