import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[2][N + 1];
        for (int i = 1; i <= N; i++) dp[0][i] = cost[0] * i;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (j < i + 1) {
                    dp[1][j] = dp[0][j];
                } else {
                    dp[1][j] = Math.min(dp[1][j - (i + 1)] + cost[i], dp[0][j]);
                }
            }
            dp[0] = dp[1];
            dp[1] = new int[N + 1];
        }

        System.out.println(dp[0][N]);
    }
}
