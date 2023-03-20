/*
    Boj 12969. ABC
    level. gold 1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_12969_ABC {
    static int N, K;
    static boolean[][][][] dp;
    static char[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new boolean[31][31][31][451];
        ans = new char[N];

        if (bt(0, 0, 0, 0, 0)) {
            StringBuilder sb = new StringBuilder();
            for (char c : ans) sb.append(c);
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }

    }

    static boolean bt(int idx, int a, int b, int c, int k) {
        if (idx == N) {
            if (k == K) {
                return true;
            }
            return false;
        }

        if (dp[a][b][c][k]) {
            return false;
        }
        dp[a][b][c][k] = true;

        ans[idx] = 'A';
        if (bt(idx + 1, a + 1, b, c, k)) return true;

        ans[idx] = 'B';
        if (bt(idx + 1, a, b + 1, c, k + a)) return true;

        ans[idx] = 'C';
        if (bt(idx + 1, a, b, c + 1, k + a + b)) return true;

        return false;
    }
}
