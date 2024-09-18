import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        if (A == 1) {
            System.out.println(B % MOD);
            return;
        }

        long up = (pow(A, B) - 1 + MOD) % MOD;
        long down = pow(A - 1, MOD - 2);

        System.out.println((up * down) % MOD);
    }

    private static long pow(long num, long n) {
        if (n == 0) return 1;
        if (n == 1) return num;

        long half = pow(num, n / 2);

        if (n % 2 == 0) {
            return (half * half) % MOD;
        } else {
            long even = pow(num, n - 1);
            return (even * num) % MOD;
        }
    }
}
