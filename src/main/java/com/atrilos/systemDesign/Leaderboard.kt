package com.atrilos.systemDesign

/**
 * [1244](https://leetcode.com/problems/design-a-leaderboard/description/)
 */
class Leaderboard {

    private val scores = HashMap<Int, Int>()
    private val topScores = sortedSetOf<Score>()

    fun addScore(playerId: Int, score: Int) {
        if (playerId in scores) {
            val prevScore = scores[playerId]!!
            topScores.remove(Score(playerId, prevScore))
            scores[playerId] = prevScore + score
            topScores.add(Score(playerId, prevScore + score))
        } else {
            scores[playerId] = score
            topScores.add(Score(playerId, score))
        }
    }

    fun top(k: Int): Int {
        val iterator = topScores.iterator()
        var sum = 0
        repeat(k) {
            sum += iterator.next().score
        }
        return sum
    }

    fun reset(playerId: Int) {
        val prevScore = scores.remove(playerId)!!
        topScores.remove(Score(playerId, prevScore))
    }

    data class Score(val id: Int, val score: Int) : Comparable<Score> {
        override fun compareTo(other: Score): Int {
            val scoreComp = other.score.compareTo(this.score)
            return if (scoreComp == 0) this.id.compareTo(other.id) else scoreComp
        }
    }
}