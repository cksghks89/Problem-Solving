/*
    Boj 15817. 배수 공사
    level. gold 5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_15817_배수공사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] dp = new int[x + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for (int j = x; j > 0; j--) {
                for (int k = 1; k <= C; k++) {
                    if (j - L * k < 0) break;
                    dp[j] += dp[j - L * k];
                }
            }
        }

        System.out.println(dp[x]);
    }
}
