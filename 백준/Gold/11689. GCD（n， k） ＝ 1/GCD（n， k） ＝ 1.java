import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static long n;
    private static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Long.parseLong(br.readLine());

        setPrime(n);

        long answer = 1;
        for (int i = 2; i < prime.length; i++) {
            if (!prime[i] || n % i != 0) continue;

            int square = 0;
            while (n % i == 0) {
                square += 1;
                n /= i;
            }

            answer *= ((long) Math.pow(i, square) - (long) Math.pow(i, square - 1));
        }

        if (n != 1) {
            answer *= (n - 1);
        }

        System.out.println(answer);
    }

    private static void setPrime(long n) {
        prime = new boolean[(int) Math.sqrt(n) + 10];
        Arrays.fill(prime, true);

        for (int i = 2; i < prime.length; i++) {
            if (!prime[i]) continue;
            for (int j = i * 2; j < prime.length; j += i) {
                prime[j] = false;
            }
        }
    }
}
