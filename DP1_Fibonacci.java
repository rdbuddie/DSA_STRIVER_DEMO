package Dynamic_Programming;

import java.util.Scanner;

public class DP1_Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println(fibonacciRecursion(n));

        // Memoization
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }
        System.out.println(fibonacciMemoization(n, dp));

        // Tabulation
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }
        System.out.println(fibonacciTabulation(n, dp));

        sc.close();
    }

    // Recursion
    public static int fibonacciRecursion(int n) {
        if (n <= 1)
            return n;

        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    // Memoization (top-down approach)
    public static int fibonacciMemoization(int n, int[] dp) {
        if (n <= 1)
            return n;

        if (dp[n] != -1)
            return dp[n];

        return dp[n] = fibonacciMemoization(n - 1, dp) + fibonacciMemoization(n - 2, dp);
    }

    // Tabulation (bottom-up approach)
    public static int fibonacciTabulation(int n, int[] dp) {
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
