package com.atrilos.matrix;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] mtrx = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        new RotateImage().rotate(mtrx);
        System.out.println(Arrays.deepToString(mtrx));
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        transpose(matrix, n);
        reverse(matrix, n);
    }

    private void reverse(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(matrix, i, j, i, n - 1 - j);
            }
        }
    }

    private void transpose(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private void swap(int[][] matrix, int i, int j, int i2, int j2) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}
