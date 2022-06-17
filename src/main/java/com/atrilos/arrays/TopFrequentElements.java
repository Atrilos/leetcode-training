package com.atrilos.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        var map =
                Arrays.stream(nums).boxed()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (v1, v2) -> { throw new IllegalStateException(); },
                                LinkedHashMap::new
                        ));
        int[] res = new int[k];
        int i = 0;
        for (var iter = map.entrySet().iterator(); i < k; i++) {
            res[i] = iter.next().getKey();
        }
        return res;
    }
}
