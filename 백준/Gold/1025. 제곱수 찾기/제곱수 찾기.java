import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        int answer = getMaxSquare();
        System.out.println(answer);
    }

    private static int getMaxSquare() {
        int max = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d1 = -9; d1 <= 9; d1++) {
                    for (int d2 = -9; d2 <= 9; d2++) {
                        int cur = getMaxNum(i, j, d1, d2);
                        max = Math.max(max, cur);
                    }
                }
            }
        }

        return max;
    }

    private static int getMaxNum(int x, int y, int d1, int d2) {
        if (d1 == 0 && d2 == 0) {
            int sqrt = (int) Math.sqrt(board[x][y]);
            if (sqrt * sqrt == board[x][y]) {
                return board[x][y];
            }
            return -1;
        }
        String cur = "";

        int max = -1;
        while (isInRange(x, y)) {
            cur += board[x][y];

            int parseCur = Integer.parseInt(cur);
            int sqrt = (int) Math.sqrt(parseCur);
            if (sqrt * sqrt == parseCur) {
                max = Math.max(max, parseCur);
            }
            x += d1;
            y += d2;
        }

        return max;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
