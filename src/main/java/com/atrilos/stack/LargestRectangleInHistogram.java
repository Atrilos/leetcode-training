package com.atrilos.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 * <p>
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= heights.length <= 10^5
 * 0 <= heights[i] <= 10^4
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        Deque<int[]> deque = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while (!deque.isEmpty() && heights[i] < deque.getLast()[0]) {
                int[] last = deque.removeLast();
                int lastStart = last[1];
                int lastHeight = last[0];
                res = Math.max(res, (i - lastStart) * lastHeight);
                start = lastStart;
            }
            deque.add(new int[]{heights[i], start});
        }
        while (!deque.isEmpty()) {
            int[] last = deque.removeLast();
            int lastStart = last[1];
            int lastHeight = last[0];
            res = Math.max(res, (heights.length - lastStart) * lastHeight);
        }
        return res;
    }
}
