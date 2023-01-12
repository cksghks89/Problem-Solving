/*
    Boj 10830. 행렬 제곱
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10830_행렬제곱 {
    static final int MODE = 1_000;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] mat = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken()) % MODE;
            }
        }

        int[][] answer = pow(mat, B);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] pow(int[][] mat, long exp) {
        if (exp == 1L) {
            return mat;
        }

        int[][] mat1 = pow(mat, exp / 2);

        if (exp % 2 == 1) {
            return multiply(multiply(mat1, mat1), mat);
        } else {
            return multiply(mat1, mat1);
        }
    }

    static int[][] multiply(int[][] mat1, int[][] mat2) {
        int[][] rtnMat = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    rtnMat[i][j] += mat1[i][k] * mat2[k][j];
                }
                rtnMat[i][j] %= MODE;
            }
        }

        return rtnMat;
    }
}
