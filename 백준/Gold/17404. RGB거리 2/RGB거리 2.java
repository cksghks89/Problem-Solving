import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][3];
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr[i] = line;
        }

        final int INF = 11_000_000;
        int answer = INF;
        int R = 0, G = 0, B = 0;

        // 첫 시작이 R 일 경우
        dp[0][0] = arr[0][0];
        dp[0][1] = INF;
        dp[0][2] = INF;

        getDpArr(N, dp, arr);
        // 끝이 G, B
        // G
        G = Math.min(dp[N - 2][0], dp[N - 2][2]) + arr[N - 1][1];
        B = Math.min(dp[N - 2][0], dp[N - 2][1]) + arr[N - 1][2];
        answer = Math.min(answer, Math.min(G, B));

        // 첫 시작이 G 일 경우
        dp[0][0] = INF;
        dp[0][1] = arr[0][1];
        dp[0][2] = INF;

        getDpArr(N, dp, arr);
        // 끝이 R, B
        R = Math.min(dp[N - 2][1], dp[N - 2][2]) + arr[N - 1][0];
        B = Math.min(dp[N - 2][0], dp[N - 2][1]) + arr[N - 1][2];

        answer = Math.min(answer, Math.min(R, B));

        // 첫 시작이 B 일 경우
        dp[0][0] = INF;
        dp[0][1] = INF;
        dp[0][2] = arr[0][2];

        getDpArr(N, dp, arr);
        // 끝이 R, G
        R = Math.min(dp[N - 2][1], dp[N - 2][2]) + arr[N - 1][0];
        G = Math.min(dp[N - 2][0], dp[N - 2][2]) + arr[N - 1][1];
        answer = Math.min(answer, Math.min(R, G));

        System.out.println(answer);
    }

    private static void getDpArr(int N, int[][] dp, int[][] arr) {
        for (int i = 1; i < N - 1; i++) {
            dp[i][0] = arr[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = arr[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = arr[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
    }
}
