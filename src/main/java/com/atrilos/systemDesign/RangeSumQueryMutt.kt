package com.atrilos.systemDesign

/**
 * [307](https://leetcode.com/problems/range-sum-query-mutable/description/)
 */
class RangeSumQueryMutt {
    class NumArrayFenwick(nums: IntArray) {

        val a = nums
        val b = IntArray(nums.size + 1)
        val size = nums.size

        init {
            for (i in a.indices) {
                add(i + 1, a[i])
            }
        }

        private fun add(index: Int, num: Int) {
            var i = index
            while (i <= size) {
                b[i] += num
                i += i and (-i)
            }
        }

        fun update(index: Int, num: Int) {
            var i = index + 1
            val diff = num - a[index]
            a[index] = num
            while (i <= size) {
                b[i] += diff
                i += i and (-i)
            }
        }

        fun sumRange(left: Int, right: Int): Int {
            return range(right + 1) - range(left)
        }

        private fun range(index: Int): Int {
            var i = index
            var sum = 0
            while (i > 0) {
                sum += b[i]
                i -= i and (-i)
            }

            return sum
        }
    }

    class NumArraySegTree(nums: IntArray) {
        class SegmentTreeNode(val start: Int, val end: Int) {
            var left: SegmentTreeNode? = null
            var right: SegmentTreeNode? = null
            var sum: Int = 0
        }

        var root: SegmentTreeNode = buildTree(nums, 0, nums.size - 1)


        private fun buildTree(nums: IntArray, start: Int, end: Int): SegmentTreeNode {
            val ret = SegmentTreeNode(start, end)

            if (start == end) {
                ret.sum = nums[start]
            } else {
                val mid = start + (end - start) / 2
                ret.left = buildTree(nums, start, mid)
                ret.right = buildTree(nums, mid + 1, end)
                ret.sum = ret.left!!.sum + ret.right!!.sum
            }

            return ret
        }

        fun update(i: Int, num: Int) {
            update(root, i, num)
        }

        private fun update(root: SegmentTreeNode, pos: Int, num: Int) {
            if (root.start == root.end) {
                root.sum = num
            } else {
                val mid = root.start + (root.end - root.start) / 2
                if (pos <= mid) {
                    update(root.left!!, pos, num)
                } else {
                    update(root.right!!, pos, num)
                }
                root.sum = root.left!!.sum + root.right!!.sum
            }
        }

        fun sumRange(i: Int, j: Int): Int {
            return sumRange(root, i, j)
        }

        private fun sumRange(root: SegmentTreeNode, start: Int, end: Int): Int {
            if (root.end == end && root.start == start) {
                return root.sum
            } else {
                val mid = root.start + (root.end - root.start) / 2
                return if (end <= mid) {
                    sumRange(root.left!!, start, end)
                } else if (start >= mid + 1) {
                    sumRange(root.right!!, start, end)
                } else {
                    sumRange(root.right!!, mid + 1, end) + sumRange(root.left!!, start, mid)
                }
            }
        }
    }
}