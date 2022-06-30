package com.atrilos.backtracking;

import java.util.*;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class Subsets {
    private void dfs(List<List<Integer>> result, List<Integer> current, int i, int[] nums) {
        if (i >= nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // decision to include nums[i]
        current.add(nums[i]);
        dfs(result, current, i + 1, nums);

        // decision to NOT TO include nums[i]
        current.remove(current.size() - 1);
        dfs(result, current, i + 1, nums);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        dfs(result, current, 0, nums);
        return result;
    }
}
