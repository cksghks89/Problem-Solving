/*
    Boj 2407. 조합
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Boj_2407_조합 {
    static BigInteger[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dp = new BigInteger[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = new BigInteger("0");
            }
        }

        System.out.println(combination(n, m).toString());
    }

    public static BigInteger combination(int n, int m) {
        if (m == 0 || m == n) {
            return new BigInteger("1");
        }

        if (!dp[n][m].equals(new BigInteger("0"))) {
            return dp[n][m];
        }

        return dp[n][m] = combination(n - 1, m - 1).add(combination(n - 1, m));
    }
}
