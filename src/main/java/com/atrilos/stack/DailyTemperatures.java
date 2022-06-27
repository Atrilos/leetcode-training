package com.atrilos.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the <b>i</b>th day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DailyTemperatures().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        ArrayDeque<Integer[]> deque = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!deque.isEmpty() && deque.getLast()[0] < temperatures[i]) {
                Integer[] tmp = deque.removeLast();
                res[tmp[1]] = i - tmp[1];
            }
            deque.offerLast(new Integer[]{temperatures[i], i});
        }

        return res;
    }
}
