package com.atrilos.backtracking;

import java.util.*;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermutationsII {
    public static void main(String[] args) {
        System.out.println(new PermutationsII().permuteUnique(new int[]{1, 1, 2}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> combCount = new HashMap<>();
        for (int num : nums)
            combCount.merge(num, 1, Integer::sum);
        backtrack(res, new ArrayList<>(), combCount, nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, ArrayList<Integer> list, HashMap<Integer, Integer> combCount, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (Map.Entry<Integer, Integer> entry : combCount.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == 0) {
                continue;
            }
            list.add(key);
            combCount.merge(key, -1, Integer::sum);
            backtrack(res, list, combCount, nums);
            combCount.merge(key, 1, Integer::sum);
            list.remove(list.size() - 1);
        }
    }
}
