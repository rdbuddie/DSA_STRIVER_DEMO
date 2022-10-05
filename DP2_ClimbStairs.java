package Dynamic_Programming;

import java.util.Scanner;

/*
 * Return all possible ways a frog can jump from 0 to n 
 * if the frog is only allowed to jump in steps of 1 or 2
 */
public class DP2_ClimbStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println("total possible ways: " + climbStairsRecursion(n));

        sc.close();

    }

    // Recursion
    public static int climbStairsRecursion(int n) {
        if (n == 0 || n == 1)
            return 1; // only one possible way

        int singleJump = climbStairsRecursion(n - 1);
        int doubleJump = climbStairsRecursion(n - 2);

        return singleJump + doubleJump;
    }

}
