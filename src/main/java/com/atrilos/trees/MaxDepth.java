package com.atrilos.trees;

import java.util.AbstractMap;
import java.util.ArrayDeque;

/**
 * Given the root of a binary tree, return its maximum depth.
 * <p>
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 * <p>
 * Input: root = [1,null,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 10^4].
 * -100 <= Node.val <= 100
 */
public class MaxDepth {

    public static void main(String[] args) {
        MaxDepth ibt = new MaxDepth();
        TreeNodeJava root = new TreeNodeJava(1);
        root.left = new TreeNodeJava(2);
        root.right = new TreeNodeJava(3);
        root.left.left = new TreeNodeJava(4);
        root.right.right = new TreeNodeJava(5);
        System.out.println(ibt.maxDepth3(root));
    }

    /**
     * DFS recursive - Depth First Search
     * 1ms
     */
    public int maxDepth(TreeNodeJava root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }

    /**
     * BFS - Breadth First Search
     * 1ms
     */
    @SuppressWarnings("all")
    public int maxDepth2(TreeNodeJava root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        ArrayDeque<TreeNodeJava> deque = new ArrayDeque<>();
        deque.offerLast(root);

        while (!deque.isEmpty()) {
            for (int i = 0, size = deque.size(); i < size; i++) {
                TreeNodeJava tmp = deque.pollLast();
                if (tmp.left != null)
                    deque.offerFirst(tmp.left);
                if (tmp.right != null)
                    deque.offerFirst(tmp.right);
            }
            ++depth;
        }
        return depth;
    }

    /**
     * DFS iterative
     * 4ms
     */
    public int maxDepth3(TreeNodeJava root) {
        int depth = 0;
        ArrayDeque<AbstractMap.SimpleEntry<TreeNodeJava, Integer>> deque = new ArrayDeque<>();
        deque.offerLast(new AbstractMap.SimpleEntry<>(root, 1));

        while (!deque.isEmpty()) {
            var tmp = deque.pollLast();
            if (tmp.getKey() != null) {
                depth = Math.max(tmp.getValue(), depth);
                deque.offerFirst(new AbstractMap.SimpleEntry<>(tmp.getKey().left, tmp.getValue() + 1));
                deque.offerFirst(new AbstractMap.SimpleEntry<>(tmp.getKey().right, tmp.getValue() + 1));
            }
        }
        return depth;
    }
}
