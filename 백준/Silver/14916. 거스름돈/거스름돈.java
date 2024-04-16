import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int INF = 100_000;
        int[] dp = new int[100_001];
        Arrays.fill(dp, INF);
        dp[2] = 1;
        dp[4] = 2;
        dp[5] = 1;

        for (int i = 6; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2] + 1, dp[i - 5] + 1);
        }

        System.out.println(dp[n] >= INF ? -1 : dp[n]);
    }
}
