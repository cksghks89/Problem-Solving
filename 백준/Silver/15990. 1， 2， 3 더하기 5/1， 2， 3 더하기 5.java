import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int MOD = 1_000_000_009;
        long[][] memo = new long[100_001][4];
        memo[1][1] = 1;
        memo[2][2] = 1;
        memo[3][1] = 1;
        memo[3][2] = 1;
        memo[3][3] = 1;

        for (int i = 4; i <= 100_000; i++) {
            memo[i][1] = (memo[i - 1][2] + memo[i - 1][3]) % MOD;
            memo[i][2] = (memo[i - 2][1] + memo[i - 2][3]) % MOD;
            memo[i][3] = (memo[i - 3][1] + memo[i - 3][2]) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int cur = Integer.parseInt(br.readLine());
            long curAnswer = (memo[cur][1] + memo[cur][2] + memo[cur][3]) % MOD;

            sb.append(curAnswer).append('\n');
        }

        System.out.println(sb);
    }
}
