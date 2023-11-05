package com.atrilos.dp1d

/**
 * [2272](https://leetcode.com/problems/substring-with-largest-variance/description/)
 */
class SubstringLargestVariance {
    fun largestVariance(s: String): Int {
        val dict = mutableMapOf<Char, Int>()
        s.forEach { dict[it] = dict.getOrDefault(it, 0) + 1 }
        var globalMax = 0

        for ((major, _) in dict) {
            for ((minor, minorFreq) in dict) {
                if (major == minor) {
                    continue
                }
                var countMajor = 0
                var countMinor = 0
                var restMinor = minorFreq
                for (ch in s) {
                    if (ch == major) {
                        countMajor++
                    } else if (ch == minor) {
                        countMinor++
                        restMinor--
                    } else {
                        continue
                    }
                    if (countMinor > 0) {
                        globalMax = maxOf(globalMax, countMajor - countMinor)
                    }
                    if (countMajor - countMinor < 0 && restMinor > 0) {
                        countMinor = 0
                        countMajor = 0
                    }
                }
            }
        }

        return globalMax
    }
}