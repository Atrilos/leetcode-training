package com.atrilos.stack


/**
 * [1291](https://leetcode.com/problems/sequential-digits/description/)
 */
class SequentialDigits {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val queue = mutableListOf(
            1, 2, 3, 4, 5, 6, 7, 8
        )
        val answer = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val n = queue.removeFirst()
            val lastDigit = n % 10 + 1
            val nextNumber = n * 10 + lastDigit
            if (lastDigit > 9) continue
            if (nextNumber > high) break
            if (nextNumber >= low)
                answer.add(nextNumber)
            queue.add(nextNumber)
        }

        return answer
    }

}