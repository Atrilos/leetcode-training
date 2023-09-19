package com.atrilos.slidingWindow

/**
 * [3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
 */
fun lengthOfLongestSubstring(s: String): Int {
    val dict = IntArray(1000)
    var l = 0
    var r = 0
    var res = 0
    while (r < s.length) {
        val current = s[r].code
        dict[current]++
        if (dict[current] == 1) {
            res = maxOf(res, r - l + 1)
        } else {
            while (l <= r && dict[current] > 1) {
                dict[s[l].code]--
                l++
            }
        }
        r++
    }

    return res
}