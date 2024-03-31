package com.atrilos.systemDesign

import java.lang.Math.random


/**
 * [1206](https://leetcode.com/problems/design-skiplist/description/)
 */
class Skiplist {
    data class Node(private val level: Int, val key: Int) {
        val forward: Array<Node> = Array(level) { dummy }
        var value: Int = 1
    }

    private val header = Node(MAX_LEVEL, -1)
    private var listLevel = 1

    fun search(target: Int): Boolean {
        var cur: Node = header

        for (i in listLevel - 1 downTo 0) {
            while (cur.forward[i].key < target) {
                cur = cur.forward[i]
            }
        }

        return cur.forward[0].key == target
    }

    fun add(num: Int) {
        val update = getPredecessors(num)
        var cur = update[0].forward[0]

        if (cur.key == num) {
            cur.value++
        } else {
            val level = randomLevel()
            if (level > listLevel) {
                listLevel = level
            }
            cur = Node(level, num)
            for (i in 0..<level) {
                cur.forward[i] = update[i].forward[i]
                update[i].forward[i] = cur
            }
        }
    }

    fun erase(num: Int): Boolean {
        val update = getPredecessors(num)
        val cur = update[0].forward[0]
        if (cur.key != num) {
            return false
        } else {
            if (cur.value > 1) {
                cur.value--
            } else {
                for (i in 0..<listLevel) {
                    if (update[i].forward[i] !== cur) {
                        break
                    }
                    update[i].forward[i] = cur.forward[i]
                }
                while (listLevel > 1 && header.forward[listLevel - 1] === dummy) {
                    listLevel--
                }
            }
            return true
        }
    }

    private fun getPredecessors(target: Int): Array<Node> {
        val update = Array(MAX_LEVEL) { Node(MAX_LEVEL, -1) }
        var cur: Node = header

        for (i in listLevel - 1 downTo 0) {
            while (cur.forward[i].key < target) {
                cur = cur.forward[i]
            }
            update[i] = cur
        }

        return update
    }

    private fun randomLevel(): Int {
        var level = 1

        while (random() < RANDOM_FACTOR && level < minOf(MAX_LEVEL, listLevel + 1)) {
            ++level
        }

        return level
    }

    companion object {
        const val MAX_LEVEL = 16
        const val RANDOM_FACTOR = 0.5
        val dummy: Node = Node(0, Int.MAX_VALUE)
    }
}

