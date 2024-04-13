import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int K;
    private static int[] arr;
    private static int[][] dp;
    private static int[][] sumMemo;
    private static int cnt = 0;
    private static int cnt2 = 0;
    private static int cnt3 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            K = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp = new int[K][K];
            sumMemo = new int[K][K];
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < K; j++) {
                    if (i == j) continue;
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            int answer = recur(0, K - 1);
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    private static int recur(int start, int end) {
        if (start == end) {
            return 0;
        }

        if (dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }

        for (int i = start; i < end; i++) {
            if (sumMemo[start][end] == 0) sumMemo[start][end] = sum(start, end);
            dp[start][end] = Math.min(dp[start][end], recur(start, i) + recur(i + 1, end) + sumMemo[start][end]);
        }
        return dp[start][end];

    }

    private static int sum(int s, int e) {
        int sum = 0;
        for (int i = s; i <= e; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
