import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int end = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N - 1][21];

        dp[0][nums[0]] = 1;

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];

            for (int j = 0; j <= 20; j++) {
                if (0 <= j + cur && j + cur <= 20) {
                    dp[i][j + cur] += dp[i - 1][j];
                }
                if (0 <= j - cur && j - cur <= 20) {
                    dp[i][j - cur] += dp[i - 1][j];
                }
            }
        }

        long result = dp[N - 2][end];

        System.out.println(result);
    }
}
