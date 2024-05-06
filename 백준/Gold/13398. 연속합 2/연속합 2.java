import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = getMaxSum(arr);

        // reverse
        int[] reverseArr = new int[n];
        for (int i = 0; i < n; i++) {
            reverseArr[i] = arr[n - i - 1];
        }
        answer = Math.max(answer, getMaxSum(reverseArr));

        System.out.println(answer);
    }

    private static int getMaxSum(int[] arr) {
        int[][] dp = new int[arr.length][2];

        dp[0][0] = arr[0];
        dp[0][1] = arr[0];

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        return max;
    }
}
