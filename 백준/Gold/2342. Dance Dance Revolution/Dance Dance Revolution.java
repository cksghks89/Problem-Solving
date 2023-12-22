import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] arr;
    private static long[][][] dp;
    private static final long INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        arr = Arrays.copyOf(arr, arr.length - 1);
        N = arr.length;
        dp = new long[N + 1][5][5];

        // dp 배열의 각 원소를 최대값으로 세팅
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[0][0][0] = 0;

        for (int i = 1; i <= N; i++) {
            int dest = arr[i - 1];

            // 왼발을 이동할 경우
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    // j -> dest 로 이동
                    if (j == 0) {
                        dp[i][dest][k] = Math.min(dp[i - 1][0][k] + 2, dp[i][dest][k]);
                    } else if (j == dest) {
                        dp[i][dest][k] = Math.min(dp[i - 1][dest][k] + 1, dp[i][dest][k]);
                    } else if (Math.abs(dest - j) % 2 == 0) {
                        dp[i][dest][k] = Math.min(dp[i - 1][j][k] + 4, dp[i][dest][k]);
                    } else {
                        dp[i][dest][k] = Math.min(dp[i - 1][j][k] + 3, dp[i][dest][k]);
                    }
                }
            }

            // 오른발을 이동할 경우
            // 왼발을 이동할 경우
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    // j -> dest 로 이동
                    if (j == 0) {
                        dp[i][k][dest] = Math.min(dp[i - 1][k][0] + 2, dp[i][k][dest]);
                    } else if (j == dest) {
                        dp[i][k][dest] = Math.min(dp[i - 1][k][dest] + 1, dp[i][k][dest]);
                    } else if (Math.abs(dest - j) % 2 == 0) {
                        dp[i][k][dest] = Math.min(dp[i - 1][k][j] + 4, dp[i][k][dest]);
                    } else {
                        dp[i][k][dest] = Math.min(dp[i - 1][k][j] + 3, dp[i][k][dest]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                answer = (int) Math.min(answer, dp[N][i][j]);
            }
        }
        System.out.println(answer);
    }
}
