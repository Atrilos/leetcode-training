package com.atrilos.graphs

/**
 * [277](https://leetcode.com/problems/find-the-celebrity/)
 */
class FindCelebrity: Relation() {
    override fun findCelebrity(n: Int) : Int {
        var candidate = 0
        for(i in 1 until n) {
            // If candidate knows i then candidate ruled out else i
            if (knows(candidate, i)) {
                candidate = i
            }
        }

        for(i in 0 until n) {
            // Still need to check only candidate for appropriateness
            if (i != candidate && knows(candidate, i) || !knows(i, candidate)) {
                return -1
            }
        }

        return candidate
    }
}

open class Relation {
    fun knows(a: Int, b: Int) : Boolean {
        TODO()
    }

    open fun findCelebrity(n: Int) : Int {
        TODO()
    }
}