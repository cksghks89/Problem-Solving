import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] benefits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        benefits = new int[M + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                benefits[j][cost] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[M + 1][N + 1];
        int[][] trace = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k <= j; k++) {
                    if (dp[i][j] < benefits[i][k] + dp[i - 1][j - k]) {
                        dp[i][j] = benefits[i][k] + dp[i - 1][j - k];
                        trace[i][j] = k;
                    }
                }
            }
        }

        int cost = N;
        int[] costResult = new int[M];
        for (int i = M; i >= 1; i--) {
            costResult[i - 1] = trace[i][cost];
            cost -= trace[i][cost];
        }

        System.out.println(dp[M][N]);
        for (int i = 0; i < M; i++) {
            System.out.print(costResult[i] + " ");
        }
    }
}

