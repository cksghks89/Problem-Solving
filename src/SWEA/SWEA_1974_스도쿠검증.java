package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_1974_스도쿠검증 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int[][] sudoku = new int[9][9];

            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("#" + tc + " ").append(validateSudoku(sudoku)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int validateSudoku(int[][] sudoku) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        Set<Integer> boxSet = new HashSet<>();

        // row, col 검증
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                rowSet.add(sudoku[i][j]);
                colSet.add(sudoku[j][i]);
            }
            if (rowSet.size() != 9 || colSet.size() != 9) {
                return 0;
            }
            rowSet.clear();
            colSet.clear();
        }

        // box 검증
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int x = i * 3; x < i * 3 + 3; x++) {
                    for (int y = j * 3; y < j * 3 + 3; y++) {
                        boxSet.add(sudoku[x][y]);
                    }
                }
                if(boxSet.size() != 9){
                    return 0;
                }
                boxSet.clear();
            }
        }

        return 1;
    }
}
