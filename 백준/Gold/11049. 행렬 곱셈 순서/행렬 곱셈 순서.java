import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[][] matrix;
    private static long[][] dp;
    private static long INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][2];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            matrix[i][0] = Integer.parseInt(line[0]);
            matrix[i][1] = Integer.parseInt(line[1]);

            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }

        recur(0, N - 1);
        System.out.println(dp[0][N - 1]);
    }

    private static long recur(int idx1, int idx2) {
        if (idx1 >= idx2) return 0;
        if (dp[idx1][idx2] != INF) {
            return dp[idx1][idx2];
        }

        for (int i = idx1; i < idx2; i++) {
            long cur = recur(idx1, i) + recur(i + 1, idx2) + (long) matrix[idx1][0] * matrix[i][1] * matrix[idx2][1];
            dp[idx1][idx2] = Math.min(dp[idx1][idx2], cur);
        }

        return dp[idx1][idx2];
    }
}
