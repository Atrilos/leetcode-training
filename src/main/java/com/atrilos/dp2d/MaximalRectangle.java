package com.atrilos.dp2d;

import java.util.Arrays;

public class MaximalRectangle {

    public static void main(String[] args) {
        System.out.println(new MaximalRectangle().maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}}));
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        final int M = matrix.length, N = matrix[0].length;
        int maxArea = 0;
        int[] height = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];
        Arrays.fill(right, N);
        for (int i = 0; i < M; i++) {
            // Get height at row i
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // Get left at row i
            int curLeft = 0;
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // Get right at row i
            int curRight = N;
            for (int j = N - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = N;
                    curRight = j;
                }
            }
            // Get max area
            for (int j = 0; j < N; j++) {
                int area = (right[j] - left[j]) * height[j];
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}
