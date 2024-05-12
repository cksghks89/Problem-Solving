import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[81][2];
        dp[1] = new long[]{1, 1};
        for (int i = 2; i <= 80; i++) {
            dp[i] = new long[]{dp[i - 1][1], dp[i - 1][1] + dp[i - 1][0]};
        }

        int N = Integer.parseInt(br.readLine());
        System.out.println(dp[N][0] * 2 + dp[N][1] * 2);
    }
}
