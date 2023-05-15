package com.atrilos.trees;

/**
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 * <p>
 * The cloned tree is a copy of the original tree.
 * <p>
 * Return a reference to the same node in the cloned tree.
 * <p>
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to
 * a node in the cloned tree.
 * <p>
 * Example 1:
 * <p>
 * Input: tree = [7,4,3,null,null,6,19], target = 3
 * Output: 3
 * Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree.
 * The answer is the yellow node from the cloned tree.
 * Example 2:
 * <p>
 * Input: tree = [7], target =  7
 * Output: 7
 * Example 3:
 * <p>
 * Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * The values of the nodes of the tree are unique.
 * target node is a node from the original tree and is not null.
 * <p>
 * <p>
 * Follow up: Could you solve the problem if repeated values on the tree are allowed?
 */
public class FindCorrespondingNodeInClone {

    private TreeNodeJava target;
    private TreeNodeJava ans;

    public final TreeNodeJava getTargetCopy(final TreeNodeJava original, final TreeNodeJava cloned, final TreeNodeJava target) {
        this.target = target;
        findTargetValue(original, cloned);
        return ans;
    }

    private void findTargetValue(TreeNodeJava original, TreeNodeJava cloned) {
        if (original != null) {
            if (original.val == target.val && isEqual(original, target)) {
                ans = cloned;
            }
            findTargetValue(original.left, cloned.left);
            findTargetValue(original.right, cloned.right);
        }
    }

    private boolean isEqual(TreeNodeJava original, TreeNodeJava target) {
        if (original == null && target == null)
            return true;
        if (original == null || target == null)
            return false;

        if (original.val == target.val) {
            return isEqual(original.left, target.left)
                    && isEqual(original.right, target.right);
        } else {
            return false;
        }
    }
}
