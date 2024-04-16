package com.atrilos.arrays

import java.util.*


/**
 * [3072](https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/description/)
 */
class DistrElementsTwoArrays2Fenwick {

    fun resultArray(nums: IntArray): IntArray {
        val arr1 = mutableListOf<Int>()
        val arr2 = mutableListOf<Int>()

        val max = nums.maxOrNull() ?: 0

        val f1 = FenwickTree(max + 1)
        val f2 = FenwickTree(max + 1)

        arr1.add(nums[0])
        f1.update(nums[0])
        arr2.add(nums[1])
        f2.update(nums[1])

        for (i in 2 until nums.size) {
            val num = nums[i]
            val count1 = f1.range(num + 1, max)
            val count2 = f2.range(num + 1, max)

            if (count1 > count2) {
                arr1.add(num)
                f1.update(num)
            } else if (count2 > count1) {
                arr2.add(num)
                f2.update(num)
            } else if (arr1.size > arr2.size) {
                arr2.add(num)
                f2.update(num)
            } else {
                arr1.add(num)
                f1.update(num)
            }
        }

        return (arr1 + arr2).toIntArray()
    }

    class FenwickTree(val size: Int) {
        val arr = mutableMapOf<Int, Int>()

        fun range(a: Int, b: Int): Int {
            return range(b) - range(a - 1)
        }

        fun range(i: Int): Int {
            var sum = 0
            var i = i

            while (i > 0) {
                sum += arr[i] ?: 0
                i -= i and (-i)
            }

            return sum
        }

        fun update(i: Int) {
            var i = i
            while (i <= size) {
                arr[i] = (arr[i] ?: 0) + 1
                i += i and (-i)
            }
        }
    }
}

class DistrElementsTwoArrays2Binary {
    private fun greaterCount(sortedList: MutableList<Int>, num: Int): Int {
        var left = 0
        var right = sortedList.lastIndex

        while (left <= right) {
            val mid = left + (right - left) / 2
            val cmp = sortedList[mid].compareTo(num)

            if (cmp < 0) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return sortedList.size - left
    }


    private fun quickInsert(sortedList: MutableList<Int>, num: Int) {
        var insertPoint = Collections.binarySearch(sortedList, num)
        if (insertPoint < 0) {
            insertPoint = -insertPoint - 1
        }
        sortedList.add(insertPoint, num)
    }

    fun resultArray(nums: IntArray): IntArray {
        val arr1 = mutableListOf(nums[0])
        val arr2 = mutableListOf(nums[1])

        val sortedList1 = mutableListOf(nums[0])
        val sortedList2 = mutableListOf(nums[1])

        for (i in 2..nums.lastIndex) {
            val cmp = greaterCount(sortedList1, nums[i] + 1).compareTo(greaterCount(sortedList2, nums[i] + 1))
            if (cmp > 0) {
                arr1 += nums[i]
                quickInsert(sortedList1, nums[i])
            } else if (cmp < 0) {
                arr2 += nums[i]
                quickInsert(sortedList2, nums[i])
            } else if (arr2.size < arr1.size) {
                arr2 += nums[i]
                quickInsert(sortedList2, nums[i])
            } else {
                arr1 += nums[i]
                quickInsert(sortedList1, nums[i])
            }
        }

        return (arr1.apply { arr1.addAll(arr2) }).toIntArray()
    }
}

class DistrElementsTwoArrays2SegTree {
    class SegmentTree(var size: Int) {

        var arr: IntArray = IntArray(2 * size)

        fun update(index: Int, num: Int) {
            var i = index + size
            arr[i] += num

            while (i > 1) {
                i /= 2
                arr[i] = arr[i * 2] + arr[i * 2 + 1]
            }
        }

        fun query(l: Int, r: Int): Int {
            var l = l + size
            var r = r + size
            var res = 0

            while (l <= r) {
                if ((l and 1) == 1) {
                    res += arr[l]
                    l++
                }
                if ((r and 1) == 0) {
                    res += arr[r]
                    r--
                }
                l = l shr 1
                r = r shr 1
            }

            return res
        }
    }

    fun resultArray(nums: IntArray): IntArray {
        val n = nums.size
        val distinctArray = nums.toSet().sorted()
        val distinctSize = distinctArray.size

        val indexMap = mutableMapOf<Int, Int>()
        for (i in 0 until distinctSize) {
            indexMap[distinctArray[i]] = i
        }

        val t1 = SegmentTree(distinctSize)
        val t2 = SegmentTree(distinctSize)
        val l1 = mutableListOf<Int>()
        val l2 = mutableListOf<Int>()

        l1.add(nums[0])
        t1.update(indexMap[nums[0]]!!, 1)
        l2.add(nums[1])
        t2.update(indexMap[nums[1]]!!, 1)

        for (i in 2 until n) {
            val curIndex = indexMap[nums[i]]!!
            val count1 = t1.query(curIndex + 1, distinctSize - 1)
            val count2 = t2.query(curIndex + 1, distinctSize - 1)

            if (count1 > count2 || (count1 == count2 && l1.size <= l2.size)) {
                l1.add(nums[i])
                t1.update(curIndex, 1)
            } else {
                l2.add(nums[i])
                t2.update(curIndex, 1)
            }
        }

        return (l1 + l2).toIntArray()
    }
}