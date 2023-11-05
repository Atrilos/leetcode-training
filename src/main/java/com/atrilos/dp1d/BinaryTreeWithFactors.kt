package com.atrilos.dp1d

/**
 * [823](https://leetcode.com/problems/binary-trees-with-factors/description/)
 */
class BinaryTreeWithFactors {
    fun numFactoredBinaryTrees(arr: IntArray): Int {
        arr.sort()

        val map = hashMapOf<Int, Long>()
        var res = 1L
        val mod = 1000000007

        arr.forEach {
            map[it] = 1L
        }
        for (i in 1..<arr.size) {
            var j = 0
            while (2 * arr[j] <= arr[i]) {
                if (arr[i] % arr[j] == 0 && arr[i] / arr[j] in map) {
                    map[arr[i]] = (map[arr[j]]!! * map[arr[i] / arr[j]]!! + map[arr[i]]!!) % mod
                }
                j++
            }
            res = (res + map[arr[i]]!!) % mod
        }

        return res.toInt()
    }
}