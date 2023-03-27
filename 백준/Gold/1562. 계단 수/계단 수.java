import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][][] dp;
    static int val = (1 << 10) - 1;
    static int mod = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int rtn = 0;
        for (int i = 1; i < 10; i++) {
            dp = new int[N][10][(1 << 10)];
            rtn += recur(1, i, (1 << i));
            rtn %= mod;
        }

        System.out.println(rtn);
    }

    static int recur(int idx, int k, int v) {
        if (idx == N && v == val) {
            return 1;
        } else if (idx == N) {
            return 0;
        }

        if (dp[idx][k][v] != 0) return dp[idx][k][v];

        if (k == 0) {
            dp[idx][k][v] = recur(idx + 1, 1, v | 1) % mod;
        } else if (k == 9) {
            dp[idx][k][v] = recur(idx + 1, 8, v | (1 << 8)) % mod;
        } else {
            dp[idx][k][v] = recur(idx + 1, k - 1, v | (1 << k - 1)) % mod
                    + recur(idx + 1, k + 1, v | (1 << k + 1)) % mod;
            dp[idx][k][v] %= mod;
        }
        return dp[idx][k][v] % mod;
    }
}
