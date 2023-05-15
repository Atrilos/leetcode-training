package com.atrilos.trees;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Example 2:
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        InvertBinaryTree ibt = new InvertBinaryTree();
        TreeNodeJava root = new TreeNodeJava(4);
        root.left = new TreeNodeJava(2);
        root.right = new TreeNodeJava(7);
        root.left.left = new TreeNodeJava(1);
        root.left.right = new TreeNodeJava(3);
        root.right.left = new TreeNodeJava(6);
        root.right.right = new TreeNodeJava(9);
        root = ibt.invertTree(root);
        System.out.println();
    }

    public TreeNodeJava invertTree(TreeNodeJava root) {
        if (root == null) {
            return null;
        }

        TreeNodeJava tmp = invertTree(root.right);
        root.right = invertTree(root.left);
        root.left = tmp;

        return root;
    }
}
