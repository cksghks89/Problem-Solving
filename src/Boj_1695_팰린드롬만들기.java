/*
    Boj 1695. 팰린드롬 만들기
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1695_팰린드롬만들기 {
    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int result = recur(0, N - 1);
        System.out.println(result);
    }

    static int recur(int n, int r) {
        if (n == r) {
            return 0;
        } else if (n + 1 == r) {
            if (arr[n] == arr[r]) return 0;
            else return 1;
        }

        if (dp[n][r] != Integer.MAX_VALUE) return dp[n][r];

        if (arr[n] == arr[r]) {
            dp[n][r] = Math.min(dp[n][r], recur(n + 1, r - 1));
        } else if (arr[n] != arr[r]) {
            dp[n][r] = Math.min(dp[n][r], recur(n + 1, r) + 1);
            dp[n][r] = Math.min(dp[n][r], recur(n, r - 1) + 1);
        }

        return dp[n][r];
    }
}
