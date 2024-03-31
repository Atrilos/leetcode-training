package com.atrilos.backtracking

import kotlin.math.pow

/**
 * [753](https://leetcode.com/problems/cracking-the-safe/description/)
 */
class CrackSafe {
    fun crackSafe(n: Int, k: Int): String {
        val pwd = StringBuilder()
        repeat(n) { pwd.append("0") }
        val seen = HashSet<String>()
        seen.add(pwd.toString())
        val targetCount = (k * 1.0).pow(n * 1.0).toInt() //k^n

        dfs(pwd, targetCount, k, n, seen)
        return pwd.toString()
    }

    fun dfs(pwd: StringBuilder, targetCount: Int, k: Int, n: Int, seen: HashSet<String>): Boolean {
        //base case: if count == targetCount
        if (seen.size == targetCount) {
            return true
        }
        val last = pwd.substring(pwd.length - (n - 1))
        //take last n-1 digit of pwd and append [0,1,...k-1]
        for (ch in '0' until '0' + k) {
            val combo = last + ch
            if (combo !in seen) {
                //add to visited
                seen.add(combo)
                //add to pwd
                pwd.append(ch)

                if (dfs(pwd, targetCount, k, n, seen)) return true
                //If the last dfs call failed
                //backtrack
                seen.remove(combo)
                val len = pwd.length
                pwd.delete(len - 1, len)
            }
        }
        return false
    }
}