import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[K + 1][25001];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= 25000; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][1] = 0;

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= 25000; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j] + j;
                }
            }

            for (int j = 1; j <= 25000; j++) {
                for (int k = 0; k < N; k++) {
                    if (dp[i - 1][j] >= A[k] && j + B[k] <= 25000) {
                        dp[i][j + B[k]] = Math.max(dp[i][j + B[k]], dp[i - 1][j] - A[k]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= 25000; i++) {
            max = Math.max(max, dp[K][i]);
        }

        System.out.println(max);
    }
}
