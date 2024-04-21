import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private static BigInteger[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        memo = new BigInteger[10001];
        for (int i = 0; i <= 10000; i++) memo[i] = BigInteger.ZERO;
        memo[1] = BigInteger.ONE;

        for (int i = 2; i <= 10000; i++) {
            memo[i] = memo[i - 1].add(memo[i - 2]);
        }

        System.out.println(memo[n].toString());
    }
}
