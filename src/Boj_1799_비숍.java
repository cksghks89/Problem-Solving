/*
    Boj 1799. 비숍
    level. gold 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1799_비숍 {
    static int N;
    static int[][] map;
    static Point[] mask;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mask = new Point[N];
        cnt = 0;
        backtracking(0, 0, 0, N);

        int black = cnt;

        mask = new Point[N - 1];
        cnt = 0;
        backtracking(0, 1, 0, N - 1);

        int white = cnt;

        System.out.println(black + white);
    }

    static void backtracking(int row, int idx, int sum, int rowCnt) {
        if (row == rowCnt) {
            cnt = Math.max(cnt, sum);
            return;
        }

        int nx = idx < N ? 0 : idx - N + 1;
        int ny = idx < N ? idx : N - 1;
        while (isInRange(nx, ny)) {
            if (map[nx][ny] == 1 && isPromised(row, nx, ny)) {
                mask[row] = new Point(nx, ny);
                if (row < N / 2) {
                    backtracking(row + 1, idx + 2, sum + 1, rowCnt);
                } else {
                    backtracking(row + 1, idx + 2, sum + 1, rowCnt);
                }
            }
            nx++;
            ny--;
        }

        if (row < N / 2) {
            backtracking(row + 1, idx + 2, sum, rowCnt);
        } else {
            backtracking(row + 1, idx + 2, sum, rowCnt);
        }
    }

    private static boolean isPromised(int row, int x, int y) {
        for (int i = 0; i < row; i++) {
            Point cur = mask[i];
            if (cur != null && Math.abs(x - cur.x) == Math.abs(y - cur.y)) {
                return false;
            }
        }
        return true;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
