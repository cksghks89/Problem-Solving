package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1961_숫자배열회전 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] mat = new int[N][N];

            for (int i = 0; i < N; i++) {
                mat[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            sb.append("#"+tc+"\n");
            sb.append(rotateMatrix(N, mat));
        }

        System.out.print(sb.toString());
    }

    static String rotateMatrix(int N, int[][] mat) {
        int[][] _90 = new int[N][N];
        int[][] _180 = new int[N][N];
        int[][] _270 = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                _90[i][j] = mat[N - 1 - j][i];
                _180[i][j] = mat[N - 1 - i][N - 1 - j];
                _270[i][j] = mat[j][N - 1 - i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            Arrays.stream(_90[i]).
                    forEach(sb::append);
            sb.append(" ");

            Arrays.stream(_180[i])
                    .forEach(sb::append);
            sb.append(" ");

            Arrays.stream(_270[i])
                    .forEach(sb::append);
            sb.append("\n");
        }

        return sb.toString();
    }
}
