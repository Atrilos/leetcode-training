package com.atrilos.arrays

/**
 * https://leetcode.com/problems/minimum-penalty-for-a-shop/
 * 2483
 */
fun main() {
    println(bestClosingTime("YYNY"))
}
fun bestClosingTime(customers: String): Int {
    // Start with closing at hour 0 and assume the current penalty is 0
    var min = 0
    var minPos = 0
    var current = 0
    for (i in customers.indices) {
        // If status in hour i is 'Y', moving it to open hours decrement
        // penalty by 1. Otherwise, moving 'N' to open hours increment
        // penalty by 1.
        if (customers[i] == 'Y') {
            current--
        } else {
            current++
        }
        // Update earliestHour if a smaller penalty is encountered.
        if (current < min) {
            min = current
            minPos = i + 1
        }
    }
    return minPos
}
