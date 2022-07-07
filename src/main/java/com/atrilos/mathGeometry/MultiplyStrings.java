package com.atrilos.mathGeometry;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(new MultiplyStrings().multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int m = num1.length(), n = num2.length();
        int[] arr = new int[m + n];
        StringBuilder sb = new StringBuilder();
        sb.append(num1);
        sb.reverse();
        num1 = sb.toString();
        sb.setLength(0);
        sb.append(num2);
        sb.reverse();
        num2 = sb.toString();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                arr[i + j] += num; // Temp
                arr[i + j + 1] += arr[i + j] / 10; // Carry
                arr[i + j] = arr[i + j] % 10; // After removing carry part
            }
        }
        int i = arr.length - 1;
        if (arr[i] == 0) { // Removing leading excess zero if present
            i--;
        }
        sb.setLength(0);
        while (i >= 0) {
            sb.append(arr[i]);
            i--;
        }
        return sb.toString();
    }
}
