package com.atrilos.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 * <p>
 * Note that division between two integers should truncate toward zero.
 * <p>
 * It is guaranteed that the given RPN expression is always valid.
 * That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * <p>
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= tokens.length <= 10^4
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+", "-", "*", "/" -> {
                    int b = deque.removeLast();
                    int a = deque.removeLast();
                    switch (token) {
                        case "+" -> deque.offer(a + b);
                        case "-" -> deque.offer(a - b);
                        case "*" -> deque.offer(a * b);
                        case "/" -> deque.offer(a / b);
                    }
                }
                default -> deque.offer(Integer.valueOf(token));
            }
        }
        return deque.getLast();
    }
}
