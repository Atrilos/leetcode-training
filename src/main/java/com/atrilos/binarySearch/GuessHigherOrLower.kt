package com.atrilos.binarySearch

/**
 * The API guess is defined in the parent class.
 * @param  num   your guess
 * @return         -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * fun guess(num:Int):Int {}
 */

fun main() {
    println(guessNumber(1))
}

fun guessNumber(n: Int): Int {
    var lo = 1
    var hi = n
    while (lo <= hi) {
        val mid = (hi - lo) / 2 + lo
        val result = guess(mid)
        when (result) {
            -1 -> hi = mid
            1 -> lo = mid + 1
            else -> return mid
        }
    }
    return 0
}

fun guess(pick: Int): Int {
    return if (pick > 1) -1
    else if (pick < 1) 1
    else 0
}
