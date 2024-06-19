import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 10007;

    private static int N;
    private static long[][] combMemo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        combMemo = new long[53][53];

        long answer = 0;
        for (int i = 1; i <= 13; i++) {
            if (N - 4 * i < 0) break;
            answer += (int) Math.pow(-1, i + 1) * comb(13, i) * comb(52 - 4 * i, N - 4 * i);
            answer = (answer + MOD * MOD) % MOD;
        }

        System.out.println(answer);
    }

    private static long comb(int n, int r) {
        if (r == 0 || n == r) return combMemo[n][r] = 1;
        if (combMemo[n][r] != 0) return combMemo[n][r];
        return combMemo[n][r] = (comb(n - 1, r - 1) + comb(n - 1, r)) % MOD;
    }
}
