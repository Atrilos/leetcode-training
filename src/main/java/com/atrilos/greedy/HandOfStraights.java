package com.atrilos.greedy;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Alice has some number of cards, and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 *
 *
 *
 * Constraints:
 *
 * 1 <= hand.length <= 10^4
 * 0 <= hand[i] <= 10^9
 * 1 <= groupSize <= hand.length
 *
 *
 * Note: This question is the same as 1296: <a href="https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/">...</a>
 */
public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0)
            return false;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int i : hand) {
            freqMap.merge(i, 1, Integer::sum);
        }
        while (!freqMap.isEmpty()) {
            int key = freqMap.firstEntry().getKey();
            for (int i = 0; i < groupSize; i++) {
                if (!freqMap.containsKey(key)) {
                    return false;
                }
                freqMap.merge(key, -1, Integer::sum);
                if (freqMap.get(key) == 0) {
                    freqMap.remove(key);
                }
                key++;
            }
        }
        return true;
    }
}
