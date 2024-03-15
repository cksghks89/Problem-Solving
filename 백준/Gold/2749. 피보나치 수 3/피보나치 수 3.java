import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static long n;
    private static long[][] mat = {{1, 1}, {1, 0}};

    private static final long MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());

        if (n == 0) {
            System.out.println(0);
            return;
        } else if (n == 1) {
            System.out.println(1);
            return;
        }

        long[][] answer = matPow(n - 1);
        System.out.println(answer[0][0] % MOD);
    }

    private static long[][] matPow(long n) {
        if (n == 1) {
            return mat;
        }

        if (n % 2 == 0) {
            long[][] pow = matPow(n / 2);
            return matMul(pow, pow);
        } else {
            long[][] pow = matPow(n - 1);
            return matMul(pow, mat);
        }
    }

    private static long[][] matMul(long[][] mat1, long[][] mat2) {
        long[][] result = new long[2][2];

        result[0][0] = (mat1[0][0] * mat2[0][0] + mat1[0][1] * mat2[1][0]) % MOD;
        result[0][1] = (mat1[0][0] * mat2[0][1] + mat1[0][1] * mat2[1][1]) % MOD;
        result[1][0] = (mat1[1][0] * mat2[0][0] + mat1[1][1] * mat2[1][0]) % MOD;
        result[1][1] = (mat1[1][0] * mat2[0][1] + mat1[1][1] * mat2[1][1]) % MOD;

        return result;
    }
}
