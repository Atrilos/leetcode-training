package com.atrilos.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting
 * at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains,
 * or more formally righti - lefti + 1.
 * <p>
 * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such
 * that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.
 * <p>
 * Return an array containing the answers to the queries.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 * Example 2:
 * <p>
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 */
public class MinIntervalToIncludeEachQuery {

    public int[] minInterval(int[][] intervals, int[] queries) {
        //sort intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //add [index,query]
        int[][] q = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            q[i][0] = i;
            q[i][1] = queries[i];
        }
        //sort the queries by query val
        Arrays.sort(q, Comparator.comparingInt(a -> a[1]));
        //store the minimum intervals in the priority queue, min heap
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (a[1] - a[0])));
        int[] result = new int[queries.length];
        int j = 0;
        for (int[] ints : q) {
            int index = ints[0];
            int val = ints[1];
            // if start is less than query val, then add to pq
            while (j < intervals.length && intervals[j][0] <= val)
                pq.offer(intervals[j++]);
            //anything which has the end lesser than the val then remove it
            while (!pq.isEmpty() && pq.peek()[1] < val)
                pq.poll();
            //add difference to the result
            result[index] = pq.isEmpty() ? -1 : pq.peek()[1] - pq.peek()[0] + 1;
        }
        return result;
    }
}
