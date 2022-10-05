package Dynamic_Programming;

/*
 * https://www.codingninjas.com/codestudio/problems/ninja-s-training_3621003
 * Problem Statement:
 *      Ninja is planing this 'N' days-long training schedule. Each day, he
        can perform any one of these three activities. (Running, Fighting
        Practice or Learning New Moves). Each activity has some merit
        points on each day. As Ninja has to improve all his skills, he can't do
        the same activity in two consecutive days. Can you help Ninja find
        out the maximum merit points Ninja can earn?

        You are given a 2D array of size N*3 'POINTS' with the points
        corresponding to each day and activity. Your task is to calculate the
        maximum number of merit points that Ninja can earn.

        For Example: 
        If the given 'POINTS' array is [[1,2,5], [3,1,1]
        [3,3,3] ],the answer will be 11 as 5 + 3 + 3.
 */

public class DP7_NinjaTraining {
    public static void main(String[] args) {
        int[][] points = {
                { 2, 1, 3 },
                { 3, 4, 6 },
                { 10, 1, 6 },
                { 8, 3, 7 },
        };

        int n = points.length;

        // METHOD-1 (Recursion)
        System.out.println(ninjaTrainingRec(n - 1, 3, points));

        // METH0D-2 (Memoization)
        int[][] dp = new int[n][4];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(ninjaTrainingMem(n - 1, 3, points, dp));

        // METHOD-3 (Tabulation)
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;

                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int point = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], point);
                    }
                }
            }
        }

        System.out.println(dp[n - 1][3]);
    }

    // Recursion
    public static int ninjaTrainingRec(int day, int last, int[][] points) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }

            return maxi;
        }

        int maxi = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != last) {
                int point = points[day][task] + ninjaTrainingRec(day - 1, task, points);
                maxi = Math.max(maxi, point);
            }
        }

        return maxi;
    }

        
    // Memoization
    public static int ninjaTrainingMem(int day, int last, int[][] points, int[][] dp) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }

            return maxi;
        }

        if (dp[day][last] != -1)
            return dp[day][last];

        int maxi = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != last) {
                int point = points[day][task] + ninjaTrainingRec(day - 1, task, points);
                maxi = Math.max(maxi, point);
            }
        }

        return dp[day][last] = maxi;
    }

}
