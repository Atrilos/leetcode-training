package com.atrilos.arrays

/**
 * [189](https://leetcode.com/problems/rotate-array/)
 */
fun main() {
    val ints = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    rotate(ints, 3)
    println(ints.joinToString())
}

fun rotate(nums: IntArray, k: Int) {
    val k = k % nums.size
    var start = 0
    var new = k
    var i = 0
    while (i < nums.size - start) {
        nums[new] = nums[start].also { nums[start] = nums[new] }
        new = (new + k) % nums.size
        if (new == start) {
            new = (++start + k) % nums.size
        }
        i++
    }
}