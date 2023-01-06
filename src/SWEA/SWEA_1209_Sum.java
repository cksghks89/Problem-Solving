package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1209_Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            int[][] mat = new int[100][100];

            for (int i = 0; i < 100; i++) {
                mat[i] = Arrays.stream(br.readLine().trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            sb.append("#"+tc+" ").append(getMaxSum(mat)).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int getMaxSum(int[][] mat) {
        int result = Integer.MIN_VALUE;

        int diagonal = 0;
        int reverseDiagonal = 0;

        for (int i = 0; i < 100; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < 100; j++) {
                row += mat[i][j];
                col += mat[j][i];
                if(i == j){
                    diagonal += mat[i][j];
                }
                if(i + j == 99){
                    reverseDiagonal += mat[i][j];
                }
            }
            result = Math.max(result, row);
            result = Math.max(result, col);
        }
        result = Math.max(result, diagonal);
        result = Math.max(result, reverseDiagonal);

        return result;
    }
}
