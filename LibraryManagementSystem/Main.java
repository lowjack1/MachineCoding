package com.library.system;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 6;

        int dp[] = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int l = 1, r = i - 1;
            while (l <= r) {
                dp[i] += dp[l] + dp[r];
                l++;
                r--;
            }
        }



        System.out.println(dp[n]);
    }
}
