package com.atrilos.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ContainsDuplicate {
    /**
     * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,3,1]
     * Output: true
     * Example 2:
     * <p>
     * Input: nums = [1,2,3,4]
     * Output: false
     * Example 3:
     * <p>
     * Input: nums = [1,1,1,3,3,4,3,2,4,2]
     * Output: true
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     */
    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }
}
