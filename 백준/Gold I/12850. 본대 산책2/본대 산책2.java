import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 0-정보과학관, 1-전산관, 2-미래관, 3-신양관, 4-한경직기념관, 5-진리관, 6-형남공학관, 7-학생회관
    private static long[][] mat = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0},
    };

    private static final int SIZE = 8;
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int D = Integer.parseInt(br.readLine());

        long[][] square = matSquare(mat, D);

        System.out.println(square[0][0]);
    }

    private static long[][] matSquare(long[][] mat, int d) {
        if (d == 1) {
            return mat;
        }

        long[][] half = matSquare(mat, d / 2);
        long[][] halfMul = matMul(half, half);
        if (d % 2 == 0) {
            return halfMul;
        } else {
            return matMul(halfMul, mat);
        }
    }

    private static long[][] matMul(long[][] a, long[][] b) {
        long[][] result = new long[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) result[i][j] += (a[i][k] * b[k][j]) % MOD;
                result[i][j] %= MOD;
            }
        }
        return result;
    }
}
