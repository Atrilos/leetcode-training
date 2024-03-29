package com.atrilos.trees;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * <p>
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 10^4
 */
public class HouseRobberIII {
    public int rob(TreeNodeJava root) {
        int[] ans = helper(root);
        return Math.max(ans[0], ans[1]);
    }

    public int[] helper(TreeNodeJava root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int withRobbery = root.val + left[1] + right[1];
        int withoutRobbery = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{withRobbery, withoutRobbery};
    }
}
