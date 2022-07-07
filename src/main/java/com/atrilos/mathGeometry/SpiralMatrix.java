package com.atrilos.mathGeometry;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        System.out.println(new SpiralMatrix().spiralOrder
                (new int[][]{{2, 5, 8}, {4, 0, 1}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();

        int startCol = 0;
        int endCol =  matrix[0].length-1;
        int startRow = 0;
        int endRow = matrix.length-1;

        int len = (matrix[0].length * matrix.length);

        while(list.size() < len){

            for(int i = startCol; i <= endCol; i++){
                list.add(matrix[startRow][i]);
            }

            for(int i = startRow+1; i <= endRow; i++){
                list.add(matrix[i][endCol]);
            }

            for(int i = endCol-1; i >= startCol && list.size() < len; i--){
                list.add(matrix[endRow][i]);
            }

            for(int i = endRow-1; i > startRow && list.size() < len; i--){
                list.add(matrix[i][startCol]);
            }

            startCol++;
            startRow++;
            endRow--;
            endCol--;
        }

        return list;
    }
}
