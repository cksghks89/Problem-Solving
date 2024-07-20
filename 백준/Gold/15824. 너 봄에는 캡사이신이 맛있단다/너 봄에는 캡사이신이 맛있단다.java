import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] s;
    private static long[] power;

    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        power = new long[300001];
        power[0] = 1;
        for (int i = 1; i <= 300000; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        long plus = 0;
        long minus = 0;

        for (int i = 1; i < N; i++) {
            plus += ((power[i] - 1) * s[i]) % MOD;
            plus %= MOD;
        }

        for (int i = N - 1; i >= 0; i--) {
            minus += (s[i] * (power[N - i - 1] - 1)) % MOD;
            minus %= MOD;
        }

        long answer = (plus - minus + MOD) % MOD;
        System.out.println(answer);
    }
}
