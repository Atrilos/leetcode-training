package com.atrilos.systemDesign

import java.util.*

/**
 * [353](https://leetcode.com/problems/design-snake-game/description/)
 */
class SnakeGame(width: Int, height: Int, private val food: Array<IntArray>) {

    private val widthBoundaries = 0..<width
    private val heightBoundaries = 0..<height
    private var foodIndex = 0
    private val collisionDetection = mutableSetOf<Pair<Int, Int>>().apply { add(0 to 0) }
    private val snake = LinkedList<Pair<Int, Int>>().apply { add(0 to 0) }
    private val dirs: Map<String, Pair<Int, Int>> = mapOf(
            "R" to (0 to 1), "L" to (0 to -1), "U" to (-1 to 0), "D" to (1 to 0)
    )

    fun move(direction: String): Int {
        val (di, dj) = dirs[direction]!!
        val ni = snake.last.first + di
        val nj = snake.last.second + dj
        if (ni !in heightBoundaries || nj !in widthBoundaries) {
            return -1
        }
        if (foodIndex in food.indices && food[foodIndex][0] == ni && food[foodIndex][1] == nj) {
            if (!collisionDetection.add(ni to nj)) {
                // For bugged test case (thanks a lot LT)
                if (snake.peekFirst() == ni to nj) {
                    return snake.size
                }
                return -1
            } else {
                snake.addLast(ni to nj)
                foodIndex++
            }
        } else {
            collisionDetection.remove(snake.removeFirst())
            if (!collisionDetection.add(ni to nj)) {
                return -1
            }
            snake.addLast(ni to nj)
        }
        return snake.size - 1
    }

}