package com.atrilos.arrays

fun main() {
    println(increasingTriplet(intArrayOf(1, 1, 0, 1, -1, 2,)))
}
fun increasingTriplet(nums: IntArray): Boolean {
    var firstSmallest = Int.MAX_VALUE
    var secondSmallest = Int.MAX_VALUE

    nums.forEach { num ->
        if (num <= firstSmallest) firstSmallest = num
        else if (num <= secondSmallest) secondSmallest = num
        else return true
    }

    return false
}