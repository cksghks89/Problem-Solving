import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long A, B;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        dp = new long[100];

        init();

        System.out.println(calc(B) - calc(A - 1));
    }

    private static long calc(long num) {
        if (num == 0) return 0;
        if (num == 1) return 1;
        int square = getSquare(num);
        if (1L << square == num) return dp[square - 1] + 1;

        long next = num - (1L << square);
        return dp[square - 1] + next + calc(next) + 1;
    }

    private static int getSquare(long num) {
        int cnt = 0;
        while (num >> cnt != 1) {
            cnt += 1;
        }
        return cnt;
    }

    private static void init() {
        dp[0] = 1;

        for (int i = 1; i < 58; i++) {
            dp[i] = 2 * dp[i-1] + (1L << i);
        }
    }
}
