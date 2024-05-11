import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        int max = 1;
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] == arr[i]) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] < arr[i]) {
                dp[i][0] = 1;
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = 1;
            }

            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(max);
    }
}
