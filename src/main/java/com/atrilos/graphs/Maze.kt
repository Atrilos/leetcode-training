package com.atrilos.graphs


/**
 * [490](https://leetcode.com/problems/the-maze/description/)
 */
class Maze {

    fun hasPath(maze: Array<IntArray>, start: IntArray, destination: IntArray): Boolean {
        val dirs = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
        val seen = Array(maze.size) { BooleanArray(maze[0].size) }
        val queue = java.util.ArrayDeque<Pair<Int, Int>>().apply { add(start[0] to start[1]) }

        while (queue.isNotEmpty()) {
            val (i, j) = queue.removeLast()

            if (seen[i][j]) continue
            if (i == destination[0] && j == destination[1]) return true

            seen[i][j] = true

            for ((ii, jj) in dirs) {
                var (nextI, nextJ) = i to j

                while (nextI in maze.indices && nextJ in maze[0].indices && maze[nextI][nextJ] == 0) {
                    nextI += ii
                    nextJ += jj
                }

                nextI -= ii
                nextJ -= jj
                queue.offerFirst(nextI to nextJ)
            }
        }

        return false
    }
}

/**
 * [505](https://leetcode.com/problems/the-maze-ii/)
 */
class Maze2 {
    fun shortestDistance(maze: Array<IntArray>, start: IntArray, destination: IntArray): Int {
        val dirs = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
        val queue = java.util.PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        val dist = Array(maze.size) { IntArray(maze[0].size) { Int.MAX_VALUE } }
        dist[start[0]][start[1]] = 0
        queue.add(Triple(start[0], start[1], 0))

        while (queue.isNotEmpty()) {
            val (i, j, currDist) = queue.remove()

            if (i == destination[0] && j == destination[1]) return dist[destination[0]][destination[1]]
            if (dist[i][j] < currDist) continue

            for ((ii, jj) in dirs) {
                var (nextI, nextJ) = i to j
                var count = -1

                while (nextI in maze.indices && nextJ in maze[0].indices && maze[nextI][nextJ] == 0) {
                    nextI += ii
                    nextJ += jj
                    count++
                }

                nextI -= ii
                nextJ -= jj
                if (currDist + count < dist[nextI][nextJ]) {
                    dist[nextI][nextJ] = currDist + count
                    queue.add(Triple(nextI, nextJ, dist[nextI][nextJ]))
                }
            }
        }

        return -1
    }
}

/**
 * [499](https://leetcode.com/problems/the-maze-iii/)
 */
class Maze3 {

    data class State(val i: Int, val j: Int, val dist: Int, val path: String)

    fun findShortestWay(maze: Array<IntArray>, ball: IntArray, hole: IntArray): String {
        val dirs = listOf(
            Triple(0, 1, 'r'),
            Triple(0, -1, 'l'),
            Triple(1, 0, 'd'),
            Triple(-1, 0, 'u')
        )
        val minHeap = java.util.PriorityQueue<State>(compareBy({ it.dist }, { it.path }))
        val visited = Array(maze.size) { BooleanArray(maze[0].size) }

        minHeap.offer(State(ball[0], ball[1], 0, ""))

        while (!minHeap.isEmpty()) {
            val (i, j, dist, path) = minHeap.remove()

            if (i == hole[0] && j == hole[1]) return path
            if (visited[i][j]) continue

            visited[i][j] = true

            for ((ii, jj, pathChar) in dirs) {
                var newI = i
                var newJ = j
                var newDist = dist
                while (isValid(maze, newI + ii, newJ + jj)) {
                    newI += ii
                    newJ += jj
                    newDist++
                    if (newI == hole[0] && newJ == hole[1]) break
                }

                minHeap.offer(State(newI, newJ, newDist, path + pathChar))
            }
        }

        return "impossible"
    }

    private fun isValid(maze: Array<IntArray>, r: Int, c: Int): Boolean =
        r in maze.indices && c in maze[0].indices && maze[r][c] == 0

}