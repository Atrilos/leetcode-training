package com.atrilos.slidingWindow

/**
 * [159](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters)
 */
fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
    var l = 0
    var r = 0
    val count = LinkedHashMap<Char, Int>(2, 0.75f, true)
    var res = 0
    while (r < s.length) {
        if (count.size == 2 && s[r] !in count) {
            val (c, i) = count.iterator().next()
            count.remove(c)
            l = i + 1
        }
        count[s[r]] = r
        r++
        res = maxOf(res, r - l)
    }
    return res
}

/**
 * [340](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/)
 */
fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
    var l = 0
    var r = 0
    val count = mutableMapOf<Char, Int>()
    var res = 0
    while (r < s.length) {
        count[s[r]] = count.getOrDefault(s[r], 0) + 1
        while (count.size > k) {
            if (count[s[l]] == 1) {
                count.remove(s[l])
            } else {
                count[s[l]] = count[s[l]]!! - 1
            }
            l++
        }
        r++
        res = maxOf(res, r - l)
    }
    return res
}