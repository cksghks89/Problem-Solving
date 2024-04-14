import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, T;
    private static int[] K;
    private static int[] S;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        K = new int[N];
        S = new int[N];

        dp = new int[2][T + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            K[i] = Integer.parseInt(st.nextToken());
            S[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= T; j++) {
                if (j < K[i]) {
                    dp[1][j] = dp[0][j];
                } else {
                    dp[1][j] = Math.max(dp[0][j], dp[0][j - K[i]] + S[i]);
                }
            }
            dp[0] = dp[1];
            dp[1] = new int[T + 1];
        }

        System.out.println(dp[0][T]);
    }

}
