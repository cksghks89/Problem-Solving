package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1952_수영장_subset {
    static int minCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] plan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] monthCost = getMonthCost(cost, plan);

            minCost = Integer.MAX_VALUE;
            subset(1, 0, monthCost, cost);

            sb.append(String.format("#%d %d\n", tc, Math.min(minCost, cost[3])));
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

    static void subset(int cnt, int sum, int[] monthCost, int[] cost) {
        if (cnt > 12) {
            minCost = Math.min(minCost, sum);
            return;
        }

        subset(cnt + 1, sum + monthCost[cnt], monthCost, cost);
        subset(cnt + 3, sum + cost[2], monthCost, cost);
    }
}
