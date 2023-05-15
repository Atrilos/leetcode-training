package com.atrilos.trees;

public class TreeNodeJava {
    public int val;
    public TreeNodeJava left;
    public TreeNodeJava right;

    TreeNodeJava() {
    }

    TreeNodeJava(int val) {
        this.val = val;
    }

    TreeNodeJava(int val, TreeNodeJava left, TreeNodeJava right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
