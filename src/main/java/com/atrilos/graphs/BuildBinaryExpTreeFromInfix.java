package com.atrilos.graphs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/">1597</a>
 */
public class BuildBinaryExpTreeFromInfix {
    public Node expTree(String s) {
        s = '(' + s + ')';
        Deque<Node> nodes = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();
        Map<Character, Integer> priority = Map.of('+', 0, '-', 0, '*', 1, '/', 1);

        for (char c : s.toCharArray())
            if (Character.isDigit(c)) {
                nodes.push(new Node(c));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    nodes.push(buildNode(ops.pop(), nodes.pop(), nodes.pop()));
                }
                ops.pop();  // remove '('
            } else {        // c == '+' || c == '-' || c == '*' || c == '/'
                while (!ops.isEmpty() && ops.peek() != '(' && priority.get(ops.peek()) >= priority.get(c)) {
                    nodes.push(buildNode(ops.pop(), nodes.pop(), nodes.pop()));
                }
                ops.push(c);
            }

        return nodes.peek();
    }

    private Node buildNode(char op, Node right, Node left) {
        return new Node(op, left, right);
    }

    static class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
