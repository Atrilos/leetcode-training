package com.atrilos.trees;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: root = []
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5000].
 * -10^4 <= Node.val <= 10^4
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(computeHeight(root.left) - computeHeight(root.right)) <= 1
                && isBalanced(root.right) && isBalanced(root.left);
    }

    private int computeHeight(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(computeHeight(node.right), computeHeight(node.left)) + 1;
    }
}
