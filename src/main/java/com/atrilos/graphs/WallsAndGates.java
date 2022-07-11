package com.atrilos.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * <p>
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF
 * <p>
 * Example1
 * <p>
 * Input:
 * [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output:
 * [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * <p>
 * Explanation:
 * the 2D grid is:
 * <p>
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 * <p>
 * the answer is:
 * <p>
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 * <p>
 * Example2
 * <p>
 * Input:
 * [[0,-1],[2147483647,2147483647]]
 * Output:
 * [[0,-1],[1,2]]
 */
public class WallsAndGates {
    private final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    q.add(new int[]{i, j, 0});
                }
            }
        }
        while (!q.isEmpty()) {
            int i = q.element()[0];
            int j = q.element()[1];
            int distance = q.remove()[2];
            rooms[i][j] = Math.min(rooms[i][j], distance);
            for (int[] dir : DIRS) {
                if (isValid(rooms, i + dir[0], j + dir[1]))
                    q.add(new int[]{i + dir[0], j + dir[1], distance + 1});
            }
        }
    }

    private boolean isValid(int[][] rooms, int i, int j) {
        return Math.min(i, j) >= 0 && i < rooms.length && j < rooms[0].length && rooms[i][j] == Integer.MAX_VALUE;
    }
}
