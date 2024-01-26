import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] coin;
    private static int target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            coin = new int[N];
            int[][] dp = new int[N][10001];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
                dp[i][coin[i]] = 1;
            }
            target = Integer.parseInt(br.readLine());

            for (int i = coin[0]; i <= 10000; i += coin[0]) {
                dp[0][i] = 1;
            }

            for (int i = 1; i < coin.length; i++) {
                int cur = coin[i];
                for (int j = 1; j <= 10000; j++) {
                    dp[i][j] += dp[i - 1][j];
                    if (j - cur > 0) {
                        dp[i][j] += dp[i][j - cur];
                    }
                }
            }
            sb.append(dp[N - 1][target]).append('\n');
        }
        System.out.println(sb);
    }
}
