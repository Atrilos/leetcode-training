package com.atrilos.arrays


/**
 * [2261](https://leetcode.com/problems/k-divisible-elements-subarrays/description/)
 */
class KDivisibleElementsSubarray {

    // Solution with trie T = O(N^2), S = O(N^2)
    fun countDistinctTrie(nums: IntArray, k: Int, p: Int): Int {
        val maxP = 200
        val child = mutableListOf<IntArray>()

        child.add(IntArray(maxP + 1))

        var nxt = 1
        var ans = 0

        for (i in nums.indices) {
            var count = k
            var node = 0
            for (j in i..nums.lastIndex) {
                val c = nums[j]

                if (c % p == 0) {
                    count--
                    if (count < 0) {
                        break
                    }
                }

                if (child[node][c] == 0) {
                    ans++
                    child[node][c] = nxt++
                    child.add(IntArray(maxP + 1))
                }
                node = child[node][c]
            }
        }

        return ans
    }

    // Solution with rolling hash T = O(N^2), S = O(N^2)
    fun countDistinctRabinKarp(nums: IntArray, k: Int, p: Int): Int {
        val base = 201
        val mod = 1000000009
        val map = mutableMapOf<Int, MutableList<IntRange>>()
        var res = 0

        for (i in nums.indices) {
            var hash = 0
            var count = k

            for (j in i..nums.lastIndex) {
                if (nums[j] % p == 0) {
                    count--
                    if (count < 0) {
                        break
                    }
                }
                hash = ((hash.toLong() * base + nums[j]) % mod).toInt()
                if (hash !in map) {
                    res++
                    map[hash] = mutableListOf(i..j)
                } else {
                    val entry = nums.copyOfRange(i, j + 1)
                    var found = false
                    for (compareRange in map[hash]!!) {
                        if (entry.contentEquals(nums.copyOfRange(compareRange.first, compareRange.last + 1))) {
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        map[hash]!!.add(i..j)
                        res++
                    }
                }
            }
        }

        return res
    }

    // Solution with LCP T = S = O(N)
    fun countDistinctLCP(nums: IntArray, k: Int, p: Int): Int {
        val n = nums.size
        if (n == 0) return 0

        if (n == 1) {
            return if (k >= 1 || nums[0] % p != 0) 1
            else 0
        }

        val maxLength = IntArray(n)
        var r = nums.lastIndex
        var count = k

        for (l in nums.indices.reversed()) {
            if (nums[l] % p == 0) {
                count--
                while (count < 0) {
                    if (nums[r] % p == 0) {
                        count++
                    }
                    r--
                }
            }
            maxLength[l] = r - l + 1
        }

        val suffixArray: IntArray = buildSuffixArray(nums)
        val invSuffixArray: IntArray = inverseSuffixArray(suffixArray)
        val lcp: IntArray = buildLongestCommonPrefixArray(nums, suffixArray, invSuffixArray)

        var ans = maxLength[suffixArray[n - 1]]

        for (sortedPos in n - 2 downTo 0) {
            val maxLen = maxLength[suffixArray[sortedPos]]
            val commonLength = lcp[sortedPos]
            if (commonLength < maxLen) {
                ans += maxLen - commonLength
            }
        }

        return ans
    }

    // arr.length>=2, s[i]>=1, s[i]<=K
    private fun buildSuffixArray(arr: IntArray): IntArray {
        val n = arr.size
        val s = arr.copyOf(n + 3)
        val sa = IntArray(n)

        suffixArray(s, sa, n, 200)

        return sa
    }

    private fun inverseSuffixArray(suffixArray: IntArray): IntArray {
        val n = suffixArray.size
        val ans = IntArray(n)

        for (i in 0 until n) {
            ans[suffixArray[i]] = i
        }

        return ans
    }

    private fun buildLongestCommonPrefixArray(
        arr: IntArray,
        suffixArray: IntArray,
        invSuffixArray: IntArray
    ): IntArray {
        val n = arr.size
        val lcp = IntArray(n - 1)
        var k = 0
        for (i in 0 until n) {
            if (k > 0) k--
            if (invSuffixArray[i] == n - 1) {
                k = 0
            } else {
                val j = suffixArray[invSuffixArray[i] + 1]
                while (i + k < n && j + k < n && arr[i + k] == arr[j + k]) k++
                lcp[invSuffixArray[i]] = k
            }
        }
        return lcp
    }

    //Lexicographic order for pairs.
    private fun leq(a1: Int, a2: Int, b1: Int, b2: Int): Boolean {
        return (a1 < b1 || (a1 == b1 && a2 <= b2))
    }

    //Lexicographic order for triples.
    private fun leq(a1: Int, a2: Int, a3: Int, b1: Int, b2: Int, b3: Int): Boolean {
        return (a1 < b1 || (a1 == b1 && leq(a2, a3, b2, b3)))
    }

    private fun suffixArray(s: IntArray, sa: IntArray, n: Int, k: Int) {
        val n0 = (n + 2) / 3
        val n1 = (n + 1) / 3
        val n2 = n / 3
        val n02 = n0 + n2
        val s12 = IntArray(n02 + 3)
        val sa12 = IntArray(n02 + 3)
        val s0 = IntArray(n0)
        val sa0 = IntArray(n0)

        run {
            var i = 0
            var j = 0
            while (i < n + (n0 - n1)) {
                if (i % 3 != 0) s12[j++] = i
                i++
            }
        }

        radixPass(s12, sa12, s, 2, n02, k)
        radixPass(sa12, s12, s, 1, n02, k)
        radixPass(s12, sa12, s, 0, n02, k)

        // find lexicographic names of triples
        var name = 0
        var c0 = -1
        var c1 = -1
        var c2 = -1
        for (i in 0 until n02) {
            if (s[sa12[i]] != c0 || s[sa12[i] + 1] != c1 || s[sa12[i] + 2] != c2) {
                name++
                c0 = s[sa12[i]]
                c1 = s[sa12[i] + 1]
                c2 = s[sa12[i] + 2]
            }
            if (sa12[i] % 3 == 1) {
                s12[sa12[i] / 3] = name
            } // left half
            else {
                s12[sa12[i] / 3 + n0] = name
            } // right half
        }

        // recurse if names are not yet unique
        if (name < n02) {
            suffixArray(s12, sa12, n02, name)
            // store unique names in s12 using the suffix array
            for (i in 0 until n02) s12[sa12[i]] = i + 1
        } else  // generate the suffix array of s12 directly
            for (i in 0 until n02) sa12[s12[i] - 1] = i

        // stably sort the mod 0 suffixes from SA12 by their first character
        var i = 0
        var j = 0
        while (i < n02) {
            if (sa12[i] < n0) s0[j++] = 3 * sa12[i]
            i++
        }
        radixPass(s0, sa0, s, 0, n0, k)

        // merge sorted SA0 suffixes and sorted SA12 suffixes
        var p = 0
        var t = n0 - n1
        var k = 0
        while (k < n) {
            val i = getI(sa12, n0, t) // pos of current offset 12 suffix
            val j = sa0[p] // pos of current offset 0 suffix
            if (if (sa12[t] < n0) // different compares for mod 1 and mod 2 suffixes
                    leq(s[i], s12[sa12[t] + n0], s[j], s12[j / 3]) else leq(
                    s[i],
                    s[i + 1], s12[sa12[t] - n0 + 1], s[j], s[j + 1], s12[j / 3 + n0]
                )
            ) { // suffix from SA12 is smaller
                sa[k] = i
                t++
                if (t == n02) // done --- only SA0 suffixes left
                {
                    k++
                    while (p < n0) {
                        sa[k] = sa0[p]
                        p++
                        k++
                    }
                }
            } else { // suffix from SA0 is smaller
                sa[k] = j
                p++
                if (p == n0) // done --- only SA12 suffixes left
                {
                    k++
                    while (t < n02) {
                        sa[k] = getI(sa12, n0, t)
                        t++
                        k++
                    }
                }
            }
            k++
        }
    }

    private fun getI(sa12: IntArray, n0: Int, t: Int): Int {
        return if (sa12[t] < n0) sa12[t] * 3 + 1 else (sa12[t] - n0) * 3 + 2
    }

    private fun radixPass(a: IntArray, b: IntArray, r: IntArray, shift: Int, n: Int, k: Int) {
        val c = IntArray(k + 1) // counter array
        for (i in 0 until n) c[r[a[i] + shift]]++ // count occurrences

        run {
            var i = 0
            var sum = 0
            while (i <= k) {
                // exclusive prefix sums
                val t = c[i]
                c[i] = sum
                sum += t
                i++
            }
        }
        for (i in 0 until n) b[c[r[a[i] + shift]]++] = a[i]
    }
}