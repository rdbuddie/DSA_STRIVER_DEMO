package Dynamic_Programming;

/*
 * Problem Statement:
 *      There is a frog on the 1st step of an N stairs long staircase. The frog wants to reach the Nth
        stair. HEIGHT[i] is the height of the (i+1)th stair.If Frog jumps from ith to jth stair, the energy
        lost in the jump is given by |HEIGHT[i-1] - HEIGHT[j-1] L.In the Frog is on ith staircase, he can
        jump either to (i+1)th stair or to (i+2)th stair. Your task is to find the minimum total energy
        used by the frog to reach from 1st stair to Nth stair.

        https://www.codingninjas.com/codestudio/problem-details/frog-jump_3621012
 */
public class DP3_FrogJump {
    public static void main(String[] args) {
        int[] height = { 30, 10, 60, 10, 60, 50 };
        System.out.println(frogJumpRec(5, height)); // METHOD-1

        int[] dp = new int[6];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(frogJumpMem(5, height, dp)); // METHOD-2

        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(frogJumpTabulation(height, dp)); // METHOD-3

        System.out.println(frogJumpTabulation2(height)); // METHOD-4
    }

    // Recursion
    public static int frogJumpRec(int index, int[] height) {
        if (index == 0)
            return 0;

        int left = Math.abs(height[index] - height[index - 1]) + frogJumpRec(index - 1, height);
        int right = Integer.MAX_VALUE;
        if (index > 1) {
            right = Math.abs(height[index] - height[index - 2]) + frogJumpRec(index - 2, height);
        }

        return Math.min(left, right);
    }

    // Memoization
    public static int frogJumpMem(int index, int[] height, int[] dp) {
        if (index == 0)
            return 0;
        if (dp[index] != -1)
            return dp[index];

        int left = Math.abs(height[index] - height[index - 1]) + frogJumpMem(index - 1, height, dp);
        int right = Integer.MAX_VALUE;
        if (index > 1) {
            right = Math.abs(height[index] - height[index - 2]) + frogJumpMem(index - 2, height, dp);
        }

        return dp[index] = Math.min(left, right);
    }

    // Dynamic Programing
    public static int frogJumpTabulation(int[] height, int[] dp) {
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            int left = Math.abs(height[i] - height[i - 1]) + dp[i - 1];
            int right = Integer.MAX_VALUE;
            if (i > 1) {
                right = Math.abs(height[i] - height[i - 2]) + dp[i - 2];
            }

            dp[i] = Math.min(left, right);
        }

        return dp[dp.length - 1];
    }

    // DP with space-optimization
    public static int frogJumpTabulation2(int[] height) {
        int prev = 0;
        int prev2 = 0;

        for (int i = 1; i < height.length; i++) {
            int left = Math.abs(height[i] - height[i - 1]) + prev;
            int right = Integer.MAX_VALUE;
            if (i > 1) {
                right = Math.abs(height[i] - height[i - 2]) + prev2;
            }

            int curri = Math.min(left, right);
            prev2 = prev;
            prev = curri;
        }

        return prev;
    }
}
