package com.atrilos.stack

/**
 * [321](https://leetcode.com/problems/create-maximum-number/description/)
 */
class CreateMaxNumber {
    fun maxNumber(nums1: IntArray, nums2: IntArray, k: Int): IntArray {
        val len1 = nums1.size
        val len2 = nums2.size
        var maxRes = IntArray(k)

        for (i in 0..k) {
            val j = k - i
            if (i <= len1 && (k - i) <= len2) {
                // Take i from nums1
                val maxLex1 = findLexMax(nums1, i)
                // Take remainder from nums2
                val maxLex2 = findLexMax(nums2, j)
                val res = merge(maxLex1, maxLex2, k)
                val compareRes = findMax(res, maxRes, 0, 0)
                if (compareRes) {
                    maxRes = res
                }
            }
        }

        return maxRes
    }


    private fun findLexMax(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        val deque = java.util.ArrayDeque<Int>()
        val res = IntArray(k)


        var rem = n - k
        for (i in 0..<n) {
            while (deque.isNotEmpty() && deque.peekLast() < nums[i] && rem > 0) {
                deque.removeLast()
                rem--
            }
            deque.offerLast(nums[i])
        }


        repeat(rem) {
            deque.removeLast()
        }
        repeat(k) {
            res[it] = deque.removeFirst()
        }

        return res
    }

    private fun merge(nums1: IntArray, nums2: IntArray, k: Int): IntArray {
        val res = IntArray(k)
        var resIndex = 0
        var i = 0
        var j = 0

        while (resIndex < res.size) {
            // True - nums1[i] > nums2[j] or nums2[j] doesn't exist
            res[resIndex++] = if (findMax(nums1, nums2, i, j)) nums1[i++] else nums2[j++]
        }

        return res
    }

    private fun findMax(arr1: IntArray, arr2: IntArray, p1: Int, p2: Int): Boolean {
        var i = p1
        var j = p2

        while (i < arr1.size && j < arr2.size) {
            if (arr1[i] < arr2[j]) {
                return false
            } else if (arr1[i] > arr2[j]) {
                return true
            } else {
                i++
                j++
            }
        }

        return i != arr1.size // tricky
    }
}
