package com.atrilos.heapPrioQueue

import java.util.*

fun main() {
    val smallestInfiniteSet = SmallestInfiniteSet()
    println(smallestInfiniteSet.popSmallest())
    smallestInfiniteSet.addBack(1)
    println(smallestInfiniteSet.popSmallest())
    println(smallestInfiniteSet.popSmallest())
    println(smallestInfiniteSet.popSmallest())
    smallestInfiniteSet.addBack(2)
    smallestInfiniteSet.addBack(3)
    println(smallestInfiniteSet.popSmallest())
    println(smallestInfiniteSet.popSmallest())
}
class SmallestInfiniteSet() {

    private val addedBack = sortedSetOf<Int>()
    private var maxPopped = 0

    fun popSmallest(): Int {
        return if (addedBack.isEmpty()) {
            ++maxPopped
        } else {
            addedBack.pollFirst()!!
        }
    }

    fun addBack(num: Int) {
        if (num <= maxPopped) addedBack += num
    }

}