/*
    Boj 12026. BOJ 거리
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_12026_BOJ거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] block = new int[N];
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == 'B') block[i] = 0;
            else if (input.charAt(i) == 'O') block[i] = 1;
            else block[i] = 2;
        }

        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            int cur = block[i];
            for (int j = i + 1; j < N; j++) {
                if (block[j] == (cur + 1) % 3) {
                    dp[j] = Math.min(dp[j], dp[i] + (j - i) * (j - i));
                }
            }
        }

        if (dp[N - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N - 1]);
        }
    }
}
