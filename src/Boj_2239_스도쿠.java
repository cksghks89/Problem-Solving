/*
    Boj 2239. 스도쿠
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj_2239_스도쿠 {
    static int[][] sudoku;
    static ArrayList<Point> blanks;
    static boolean[] visited;
    static boolean rtn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        blanks = new ArrayList<>();
        rtn = false;

        for (int i = 0; i < 9; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < 9; j++) {
                if (input[j] == '0') {
                    blanks.add(new Point(i, j));
                }
                sudoku[i][j] = input[j] - '0';
            }
        }
        visited = new boolean[blanks.size()];

        backTracking(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }

    static void backTracking(int depth) {
        if (depth == blanks.size()) {
            rtn = true;
            return;
        }

        Point cur = blanks.get(depth);

        for (int j = 1; j <= 9; j++) {
            if (isPromise(cur.x, cur.y, j)) {
                sudoku[cur.x][cur.y] = j;
                backTracking(depth + 1);
                if(rtn){
                    return;
                }
                sudoku[cur.x][cur.y] = 0;
            }
        }
    }

    static boolean isPromise(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[x][i] == num) {
                return false;
            }
            if (sudoku[i][y] == num) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[x / 3 * 3 + i][y / 3 * 3 + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
