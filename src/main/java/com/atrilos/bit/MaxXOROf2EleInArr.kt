package com.atrilos.bit

/**
 * [421](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/)
 */
class MaxXOROf2EleInArr {
    data class Node(
        var left: Node? = null,
        var right: Node? = null
    )

    class Tree {
        val root = Node()

        fun insert(n: Int) {
            var curr = root
            for(i in 31 downTo 0) {
                val set = (n shr i) and 1
                if(set == 1) {
                    if(curr.right == null) {
                        curr.right = Node()
                    }
                    curr = curr.right!!
                } else {
                    if(curr.left == null) {
                        curr.left = Node()
                    }
                    curr = curr.left!!
                }
            }
        }

        fun calcXor(n: Int): Int {
            var count = 0
            var curr: Node? = root
            for(i in 31 downTo 0) {
                if(curr == null) {
                    return count
                }
                val set = (n shr i) and 1
                if(set == 1) {
                    if(curr.left != null) {
                        count += 1 shl i
                        curr = curr.left
                    } else {
                        curr = curr.right
                    }
                } else {
                    if(curr.right != null) {
                        count += 1 shl i
                        curr = curr.right
                    } else {
                        curr = curr.left
                    }
                }
            }

            return count
        }
    }

    fun findMaximumXOR(nums: IntArray): Int {
        val tree = Tree()
        tree.insert(nums[0])
        var max = 0
        for(i in 1 until nums.size) {
            max = maxOf(max, tree.calcXor(nums[i]))
            tree.insert(nums[i])
        }

        return max
    }
}

class MaxXOROf2EleInArrFast {
    private val MAX_BIT_COUNT = 31

    fun findMaximumXOR(nums: IntArray): Int {
        var result = 0
        val set = mutableSetOf<Int>()

        val maxBitContForNums = MAX_BIT_COUNT - nums.max().countLeadingZeroBits()

        for (i in maxBitContForNums downTo 0) {
            result = result shl 1
            val next = result or 1
            set.clear()
            var j = 0
            while (j < nums.size && result != next) {
                val current = nums[j] shr i
                if (set.contains(next xor current)) {
                    result = next
                }
                set.add(current)
                j++
            }
        }

        return result
    }
}