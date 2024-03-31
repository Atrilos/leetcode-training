package com.atrilos.graphs

import java.util.HashMap

/**
 * [2246](https://leetcode.com/problems/longest-path-with-different-adjacent-characters/description/)
 */
class LongestPathWithDifferentAdjacentCharacters {
    private var ans = 1
    private lateinit var s: String
    private lateinit var parent: IntArray

    fun longestPath(parent: IntArray, s: String): Int {
        val edges = getEdges(parent)

        this.s = s
        this.ans = 1
        this.parent = parent

        dfs(edges, 0)

        return ans
    }

    private fun dfs(edges: HashMap<Int, MutableList<Int>>, i: Int): Int {
        var max = 0
        var secondMax = 0

        if (i !in edges) return 1

        for (e in edges[i]!!) {
            // it is the parent, don't iterate it
            if (parent[i] == e) continue

            val maxOfSubtree = dfs(edges, e)

            // we've iterated subtree, but we can't count it to this
            // path because adjacent label repetition
            if (s[i] == s[e]) continue

            if (maxOfSubtree > max) {
                secondMax = max
                max = maxOfSubtree
            } else if (maxOfSubtree > secondMax) {
                secondMax = maxOfSubtree
            }
        }

        ans = maxOf(ans, max + secondMax + 1)

        return max + 1
    }

    private fun getEdges(parent: IntArray): HashMap<Int, MutableList<Int>> {
        val edges = hashMapOf<Int, MutableList<Int>>()

        for (i in 1..parent.lastIndex) {
            val j = parent[i]
            edges.getOrPut(i) { arrayListOf() }.add(j)
            edges.getOrPut(j) { arrayListOf() }.add(i)
        }

        return edges
    }
}