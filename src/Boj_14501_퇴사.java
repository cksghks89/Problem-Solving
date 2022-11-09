/*
    Boj 14501. 퇴사
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_14501_퇴사 {
    static int N;
    static List<int[]> counseling;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        counseling = new ArrayList<>();
        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            counseling.add(new int[]{T, P});
        }

        System.out.println(getMaxCounselProfit());

    }

    static int getMaxCounselProfit() {
        for (int i = 0; i < N; i++) {
            int T = counseling.get(i)[0];
            int P = counseling.get(i)[1];
            if (i + T < N + 1) {
                dp[i + T] = Math.max(dp[i + T], dp[i] + P);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }

        return dp[N];
    }
}
