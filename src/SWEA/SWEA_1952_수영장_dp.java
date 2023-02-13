package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1952_수영장_dp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] plan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] monthCost = getMonthCost(cost, plan);

            sb.append(String.format("#%d %d\n", tc, getBestCost(monthCost, cost)));
        }
        System.out.print(sb.toString());
    }

    static int[] getMonthCost(int[] cost, int[] plan) {
        int[] monthCost = new int[13];
        for (int i = 1; i <= 12; i++) {
            monthCost[i] = Math.min(plan[i - 1] * cost[0], cost[1]);
        }
        return monthCost;
    }

    static int getBestCost(int[] monthCost, int[] cost) {
        int[] dp = new int[15];
        dp[1] = monthCost[1];
        dp[2] = monthCost[1] + monthCost[2];

        for (int i = 3; i <= 12; i++) {
            dp[i] = Math.min(dp[i - 1] + monthCost[i], dp[i - 3] + cost[2]);
        }
        dp[12] = Math.min(dp[12], dp[10] + cost[2]);
        dp[12] = Math.min(dp[12], dp[11] + cost[2]);

        return Math.min(dp[12], cost[3]);
    }
}
