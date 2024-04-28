import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[101][101];
        for (int i = 1; i <= 100; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], 1_000_000_000);
            }
        }

        if (dp[N][M] < K) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int aCount = N;
        int zCount = M;
        for (int i = 0; i < N + M; i++) {
            if (aCount == 0) {
                sb.append('z');
                zCount -= 1;
                continue;
            }
            if (zCount == 0) {
                sb.append('a');
                aCount -= 1;
                continue;
            }

            if (K <= dp[aCount - 1][zCount]) {
                sb.append('a');
                aCount -= 1;
            } else {
                sb.append('z');
                K -= dp[aCount - 1][zCount];
                zCount -= 1;
            }
        }

        System.out.println(sb);
    }
}
