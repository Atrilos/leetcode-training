package com.atrilos.bit

/**
 * https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/
 * 1318
 */
fun main() {
    println(minFlips(1, 2, 3))
}
fun minFlips(a: Int, b: Int, c: Int): Int {
    var flips = 0
    for (i in 0..31) {
        val aBit = a ushr i and 1
        val bBit = b ushr i and 1
        val cBit = c ushr i and 1
        when {
            cBit == 1 && aBit == 0 && bBit == 0 -> flips++
            cBit == 0 -> flips += aBit + bBit
        }
    }
    return flips
}