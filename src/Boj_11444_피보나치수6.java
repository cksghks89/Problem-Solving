/*
    Boj 11444. 피보나치수 6
    level. gold 2
    solved by 송찬환 with https://st-lab.tistory.com/252
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_11444_피보나치수6 {
    static final int MODE = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long[][] mat = {{1, 1}, {1, 0}};

        System.out.println(pow(mat, n)[0][1]);
    }

    static long[][] pow(long[][] mat, long exp) {
        if (exp == 1L) {
            return mat;
        }

        long[][] mat1 = pow(mat, exp / 2);

        if (exp % 2 == 1) {
            return multiply(multiply(mat1, mat1), mat);
        } else {
            return multiply(mat1, mat1);
        }
    }

    static long[][] multiply(long[][] mat1, long[][] mat2) {
        long[][] rtnMat = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                rtnMat[i][j] = mat1[i][0] * mat2[0][j] + mat1[i][1] * mat2[1][j];
                rtnMat[i][j] %= MODE;
            }
        }

        return rtnMat;
    }
}
