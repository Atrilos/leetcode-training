package com.atrilos.binarySearch

/**
 * [1095](https://leetcode.com/problems/find-in-mountain-array/description/)
 */
class FindInMountainArray {
    interface MountainArray {
        fun get(index: Int): Int

        fun length(): Int
    }

    private val cache = mutableMapOf<Int, Int>()

    fun findInMountainArray(target: Int, mountainArr: MountainArray): Int {
        val n = mountainArr.length()
        val summitIndex = findSummit(mountainArr, n)
        if (getCached(mountainArr, summitIndex) == target) {
            return summitIndex
        }
        val left = binarySearch(mountainArr, 0, summitIndex - 1, target, false)
        return if (left == -1) {
            binarySearch(mountainArr, summitIndex + 1, n - 1, target, true)
        } else {
            left
        }
    }

    private fun binarySearch(mountainArr: MountainArray, start: Int, end: Int, target: Int, reversed: Boolean): Int {
        var l = start
        var r = end
        while (l <= r) {
            val mid = (l + r) / 2
            val midVal = getCached(mountainArr, mid)
            if (midVal == target) {
                return mid
            } else if (reversed) {
                if (midVal < target) {
                    r = mid - 1
                } else {
                    l = mid + 1
                }
            } else {
                if (midVal < target) {
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
        }
        return -1
    }

    private fun findSummit(mountainArr: MountainArray, length: Int): Int {
        var l = 1
        var r = length - 2
        while (l <= r) {
            val mid = (l + r) / 2
            val midVal = getCached(mountainArr, mid)
            if (midVal < getCached(mountainArr, mid + 1)) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return l
    }

    private fun getCached(mountainArr: MountainArray, index: Int): Int {
        return if (index in cache) {
            cache[index]!!
        } else {
            cache[index] = mountainArr.get(index)
            cache[index]!!
        }
    }
}