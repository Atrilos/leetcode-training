package com.atrilos.trees;

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 * <p>
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in a strictly increasing order.
 */
public class SortedArrayToBST {

    public TreeNodeJava sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNodeJava sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end)
            return null;
        TreeNodeJava root;
        int mid = start + (end - start) / 2;
        root = new TreeNodeJava(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, end);

        return root;
    }
}
