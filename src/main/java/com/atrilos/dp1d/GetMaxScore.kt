package com.atrilos.dp1d

/**
 * [1537](https://leetcode.com/problems/get-the-maximum-score/description/)
 */
class GetMaxScore {
    val map = mutableMapOf<Int, Long>()
    val mod = (1e9 + 7).toInt()
    fun maxSum(nums1: IntArray, nums2: IntArray): Int {
        val map1 = mutableMapOf<Int, Int>()
        val map2 = mutableMapOf<Int, Int>()
        for (i in nums1.indices) map1[nums1[i]] = i
        for (i in nums2.indices) map2[nums2[i]] = i
        return (maxOf(calSum(nums1, nums2, map1, map2, 0), calSum(nums2, nums1, map2, map1, 0)) % mod).toInt()
    }

    private fun calSum(
            nums1: IntArray,
            nums2: IntArray,
            map1: MutableMap<Int, Int>,
            map2: MutableMap<Int, Int>,
            idx: Int
    ): Long {
        if (idx == nums1.size) return 0
        if (map[nums1[idx]] != null) return map[nums1[idx]]!!

        val s1 = (calSum(nums1, nums2, map1, map2, idx + 1) + nums1[idx])
        val i2 = map2[nums1[idx]]
        val s2 = if (i2 != null) (calSum(nums2, nums1, map2, map1, i2 + 1) + nums1[idx]) else 0
        val s = maxOf(s1, s2)
        map[nums1[idx]] = s
        return map[nums1[idx]]!!
    }
}