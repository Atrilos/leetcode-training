package com.atrilos.strings

import java.util.*


/**
 * [1044](https://leetcode.com/problems/longest-duplicate-substring/description/)
 */
class LongestDupStringRabinKarp {
    companion object {
        private const val BASE = 27
        private const val MOD = 1000000009
    }

    fun longestDupSubstring(s: String): String {
        var ans = -1
        var left = 0
        var right = s.length

        while (left < right) {
            val mid = left + (right - left + 1) / 2
            val result = check(s, mid)
            if (result.first) {
                ans = result.second
                left = mid
            } else {
                right = mid - 1
            }
        }

        return if (ans == -1) "" else s.substring(ans, ans + left)
    }

    private fun compareStrings(s: String, start1: Int, start2: Int, len: Int): Boolean {
        var i = start1
        var j = start2

        while (i < start1 + len) {
            if (s[i] != s[j]) {
                return false
            }
            i++
            j++
        }

        return true
    }

    private fun check(s: String, len: Int): Pair<Boolean, Int> {
        val power = getPower(len)
        var hash = getInitialHash(s, len)
        val map = mutableMapOf<Long, MutableList<Int>>()

        map[hash] = mutableListOf(0)

        for (i in 0..<s.length - len) {
            val head = s[i] - 'a' + 1
            val tail = s[i + len] - 'a' + 1
            hash = ((BASE * (hash - head * power) + tail) % MOD)
                .let { if (it < 0) it + MOD else it }

            if (hash !in map) {
                map[hash] = mutableListOf(i + 1)
            } else {
                for (start in map[hash]!!) {
                    if (compareStrings(s, i + 1, start, len)) {
                        return true to i + 1
                    }
                }
                map[hash]!!.add(i + 1)
            }
        }

        return false to 0
    }

    private fun getInitialHash(s: String, len: Int): Long {
        var hash = 0L

        for (i in 0..<len) {
            hash = (hash * BASE + (s[i] - 'a' + 1)) % MOD
        }

        return hash
    }

    private fun getPower(len: Int): Long {
        var res = 1L
        var pow = len - 1
        var base = BASE.toLong()

        while (pow > 0) {
            if (pow % 2 == 1) {
                res = (res * base) % MOD
            }
            pow = pow shr 1
            base = (base * base) % MOD
        }

        return res
    }
}

class LongestDupStringKMP {

    //TLE
    fun longestDupSubstring(s: String): String {
        var maxLength = 0
        var resStr = ""

        for (i in s.indices) {
            val lps = lpsOf(s.substring(i))
            val (idx, len) = lps.withIndex().maxByOrNull { it.value } ?: IndexedValue(0, 0)

            if (len > maxLength) {
                resStr = s.substring(i + idx - len + 1, i + idx + 1)
                maxLength = len
            }
        }

        return resStr
    }

    private fun lpsOf(s: String): IntArray {
        val lps = IntArray(s.length)
        var i = 1
        var j = 0

        while (i < s.length) {
            if (s[i] == s[j]) {
                j++
                lps[i] = j
                i++
            } else if (j != 0) {
                j = lps[j - 1]
            } else {
                i++
            }
        }

        return lps
    }
}

class LongestDupStringSA {
    fun longestDupSubstring(s: String): String {
        val sufArray = suffixArrayOf(s)
        val lcp = lcpOf(s, sufArray)

        var idx = -1
        var maxI = 0

        for (i in lcp.indices) {
            if (lcp[i] > maxI) {
                maxI = lcp[i]
                idx = sufArray[i]
            }
        }

        return if (idx == -1) "" else s.substring(idx, idx + maxI)
    }

    private class Suffix(var index: Int, var rank: Int, var next: Int) : Comparable<Suffix> {
        override fun compareTo(other: Suffix): Int {
            if (rank != other.rank) return rank.compareTo(other.rank)
            return next.compareTo(other.next)
        }
    }

    private fun suffixArrayOf(s: String): IntArray {
        val n = s.length
        val su = Array(n) { Suffix(0, 0, 0) }

        for (i in s.indices) {
            su[i] = Suffix(i, s[i].code - '$'.code, 0)
        }
        for (i in s.indices) {
            su[i].next = (if (i + 1 < n) su[i + 1].rank else -1)
        }

        su.sort()

        val ind = IntArray(n)
        var length = 4
        while (length < 2 * n) {
            var rank = 0
            var prev = su[0].rank
            su[0].rank = 0
            ind[su[0].index] = 0
            for (i in 1 until n) {
                if (su[i].rank == prev && su[i].next == su[i - 1].next) {
                    prev = su[i].rank
                    su[i].rank = rank
                } else {
                    prev = su[i].rank
                    su[i].rank = ++rank
                }
                ind[su[i].index] = i
            }
            for (i in 0 until n) {
                val nextP = su[i].index + length / 2
                su[i].next = if (nextP < n) su[ind[nextP]].rank else -1
            }
            su.sort()
            length = length shl 1
        }

        val suf = IntArray(n)

        for (i in 0 until n) {
            suf[i] = su[i].index
        }

        return suf
    }

    fun search(pat: String, txt: String, sufArr: IntArray): Int {
        val n = txt.length
        val m = pat.length
        var l = 0
        var r = n - 1

        while (l <= r) {
            val mid = l + (r - l) / 2
            val res = txt.substring(sufArr[mid], minOf(sufArr[mid] + m, n))

            if (res == pat) {
                return sufArr[mid]
            }
            if (res < pat) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }

        return -1
    }

    private fun lcpOf(txt: String, suffixArr: IntArray): IntArray {
        val n = suffixArr.size
        val lcp = IntArray(n)
        val invSuf = IntArray(n)

        for (i in suffixArr.indices) {
            invSuf[suffixArr[i]] = i
        }

        var k = 0

        for (i in suffixArr.indices) {
            if (invSuf[i] == n - 1) {
                k = 0
                continue
            }
            val j = suffixArr[invSuf[i] + 1]
            while (i + k < n && j + k < n && txt[i + k] == txt[j + k]) {
                k++
            }
            lcp[invSuf[i]] = k
            if (k > 0) {
                k--
            }
        }

        return Arrays.copyOfRange(lcp, 0, lcp.size)
    }
}