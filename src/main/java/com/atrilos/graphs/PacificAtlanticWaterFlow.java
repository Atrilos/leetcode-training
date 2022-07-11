package com.atrilos.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches
 * the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c]
 * represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west
 * if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell
 * (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Example 2:
 *
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 *
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}}));
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (i == 0 || j == 0) {
                    dfs(heights, i, j, pacific, -1);
                }
                if (i == heights.length - 1 || j == heights[i].length - 1) {
                    dfs(heights, i, j, atlantic, -1);
                }
            }
        }
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (atlantic[i][j] && pacific[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int i, int j, boolean[][] ocean, int localMin) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[i].length)
            return;
        if (heights[i][j] >= localMin && !ocean[i][j]) {
            ocean[i][j] = true;
            dfs(heights, i, j + 1, ocean, heights[i][j]);
            dfs(heights, i, j - 1, ocean, heights[i][j]);
            dfs(heights, i + 1, j, ocean, heights[i][j]);
            dfs(heights, i - 1, j, ocean, heights[i][j]);
        }
    }
}
