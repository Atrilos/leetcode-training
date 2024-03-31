package com.atrilos.backtracking

/**
 * [842](https://leetcode.com/problems/split-array-into-fibonacci-sequence/description/)
 */
class SplitArrayIntoFibonacci {
    fun splitIntoFibonacci(num: String): List<Int> {
        var maxsize = num.length / 3
        if (num.length % 2 == 1) maxsize++

        val list = mutableListOf<Int>()
        backtrack(num, list, maxsize, 0)
        return list
    }

    private fun backtrack(
        num: String,
        list: MutableList<Int>,
        maxsize: Int,
        idx: Int
    ): Boolean {
        if (idx >= num.length) {
            return true
        }


        var size = 1

        while (size <= maxsize && (idx + size) <= num.length) {
            val substring = num.substring(idx, idx + size)

            if (substring.length > 1 && substring[0] == '0') break

            var nextnum: Int
            try {
                nextnum = substring.toInt()
            } catch (e: NumberFormatException) {
                break
            }

            if (list.size < 2) {
                list.add(nextnum)
                if (backtrack(num, list, maxsize, idx + size)) return true
                list.removeLast()
            } else {
                val lastIndex = list.lastIndex
                val expected = list[lastIndex] + list[lastIndex - 1]

                if (nextnum > expected) break
                if (nextnum == expected) {
                    list.add(nextnum)
                    if (backtrack(num, list, maxsize, idx + size)) return true
                    list.removeLast()
                }
            }
            size++
        }

        return false
    }
}