import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < M; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }

        if (N == 1) {
            System.out.println(1);
            return;
        }

        int[][] dp = new int[N + 1][N + 1];
        dp[1][1] = 1;
        if (set.contains(2)) {
            dp[2][2] = 1;
        } else {
            if (!set.contains(1)) {
                dp[2][1] = 1;
            }
            dp[2][2] = 1;
        }

        for (int i = 3; i <= N; i++) {
            if (set.contains(i)) {
                int sum = Arrays.stream(dp[i - 1]).sum();
                dp[i][i] = sum;
            } else {
                dp[i][i] = dp[i - 1][i - 1] + dp[i - 1][i - 2];
                if (!set.contains(i - 1)) {
                    dp[i][i - 1] = Arrays.stream(dp[i - 2]).sum();
                }
            }
        }

        int answer = Arrays.stream(dp[N]).sum();
        System.out.println(answer);
    }
}
