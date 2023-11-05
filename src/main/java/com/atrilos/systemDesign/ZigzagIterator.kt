package com.atrilos.systemDesign

/**
 * [281](https://leetcode.com/problems/zigzag-iterator/)
 */
class ZigzagIterator(v1: IntArray, v2: IntArray) {

    private var arrays = mutableListOf<IntArray>()
    private var index = 0
    private var listIndex = 0
    private var totalCnt: Int
    private var currentElement = 0

    init {
        arrays.apply {
            if (v1.isNotEmpty()) add(v1)
            if (v2.isNotEmpty()) add(v2)
        }
        totalCnt = v1.size + v2.size
    }

    fun next(): Int {
        if (!hasNext()) {
            throw RuntimeException("Out of Elements")
        }
        val res = arrays[listIndex][index]
        currentElement++
        if (!hasNext()) {
            return res
        }
        do {
            listIndex = (listIndex + 1) % arrays.size
            if (listIndex == 0) {
                index++
            }
        } while (arrays[listIndex].size <= index)
        return res
    }

    fun hasNext(): Boolean = currentElement < totalCnt

}