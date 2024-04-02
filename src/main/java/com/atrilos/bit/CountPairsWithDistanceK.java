package com.atrilos.bit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/count-pairs-of-points-with-distance-k/">2857</a>
 */
public class CountPairsWithDistanceK {
    public static final Map<Integer, Integer> EMPTY = new HashMap<>();

    public static int countPairs(List<List<Integer>> coordinates, int k) {
        Map<Integer, Map<Integer, Integer>> counterMap = new HashMap<>();
        int result = 0;

        for (List<Integer> point : coordinates)
            result += scanPairs(counterMap, point.get(0), point.get(1), k);

        return result;
    }

    public static int scanPairs(Map<Integer, Map<Integer, Integer>> counterMap, int x1, int y1, int k) {
        int k1 = 0, k2 = k;
        int count = 0;

        while (k1 <= k) {
            int x2 = k1 ^ x1;
            int y2 = k2 ^ y1;
            count += counterMap.getOrDefault(x2, EMPTY).getOrDefault(y2, 0);
            k1++;
            k2--;
        }

        addPoint(counterMap, x1, y1);
        return count;
    }

    public static void addPoint(Map<Integer, Map<Integer, Integer>> counterMap, int x, int y) {
        counterMap.putIfAbsent(x, new HashMap<>());
        counterMap.get(x).putIfAbsent(y, 0);
        counterMap.get(x).computeIfPresent(y, (k, v) -> v + 1);
    }
}
