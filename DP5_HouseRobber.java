package Dynamic_Programming;

/*
 * Problem Statement:
 *      You are given an array/list of 'N' integers. You are supposed to return the
        maximum sum of the subsequence with the constraint that no two elements
        are adjacent in the given array/list.

        https://www.codingninjas.com/codestudio/problems/maximum-sum-of-non-adjacent-elements_843261
 */
public class DP5_HouseRobber {
    public static void main(String[] args) {
        int[] a = { 2, 1, 4, 9 };

        System.out.println(maxSum(a.length - 1, a)); // METHOD-1

        int[] dp = new int[a.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(maxSumMem(a.length - 1, a, dp)); // METHOD-2

        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(maxSumTabulation(a, dp)); // METHOD-3

        System.out.println(maxSumTabulationOptimized(a)); // METHOD-4
    }

    // Recursion
    public static int maxSum(int index, int[] a) {
        if (index == 0)
            return a[index];
        if (index < 0)
            return 0;

        int pick = a[index] + maxSum(index - 2, a);
        int notPick = 0 + maxSum(index - 1, a);

        return Math.max(pick, notPick);
    }

    // Memoization
    public static int maxSumMem(int index, int[] a, int[] dp) {
        if (index == 0)
            return a[index];
        if (index < 0)
            return 0;

        if (dp[index] != -1)
            return dp[index];

        int pick = a[index] + maxSumMem(index - 2, a, dp);
        int notPick = 0 + maxSumMem(index - 1, a, dp);

        return dp[index] = Math.max(pick, notPick);
    }

    // DP (Tabulation)
    public static int maxSumTabulation(int[] a, int[] dp) {
        dp[0] = a[0];

        for (int i = 1; i < dp.length; i++) {
            int pick = a[i];
            if (i - 2 >= 0) {
                pick += dp[i - 2];
            }

            int notPick = 0 + dp[i - 1];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[a.length - 1];
    }

    public static int maxSumTabulationOptimized(int[] a) {
        int prev = 0;
        int prev2 = 0;

        for (int i = 0; i < a.length; i++) {
            int pick = a[i];
            if (i - 2 >= 0) {
                pick += prev2;
            }

            int notPick = 0 + prev;

            int curri = Math.max(pick, notPick);

            prev2 = prev;
            prev = curri;
        }

        return prev;
    }

}
