package com.atrilos.matrix;

import java.util.ArrayDeque;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * <p>
 * You must do it in place.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 * <p>
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        ArrayDeque<int[]> zeroCoords = new ArrayDeque<>();
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0)
                    zeroCoords.add(new int[]{i, j});
            }
        }
        while (!zeroCoords.isEmpty()) {
            int[] current = zeroCoords.pop();
            for (int i = 0; i < n; i++) {
                matrix[i][current[1]] = 0;
            }
            for (int i = 0; i < m; i++) {
                matrix[current[0]][i] = 0;
            }
        }
    }
}
