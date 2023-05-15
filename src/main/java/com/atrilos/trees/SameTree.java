package com.atrilos.trees;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 * <p>
 * <p>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 */
public class SameTree {

    public static void main(String[] args) {
        TreeNodeJava t1 = new TreeNodeJava(1);
        t1.left = new TreeNodeJava(2);
        TreeNodeJava t2 = new TreeNodeJava(1);
        t2.right = new TreeNodeJava(2);
        System.out.println(new SameTree().isSameTree(t1, t2));
    }

    public boolean isSameTree(TreeNodeJava p, TreeNodeJava q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
