import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        students = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N];

        for (int i = 1; i < N; i++) {
            dp[i] = getScore(0, i);
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[j - 1] + getScore(j, i));
            }
        }

        System.out.println(dp[N - 1]);
    }

    private static int getScore(int start, int end) {
        int min = 10000;
        int max = 0;
        for (int i = start; i <= end; i++) {
            min = Math.min(min, students[i]);
            max = Math.max(max, students[i]);
        }
        return Math.abs(max - min);
    }
}
