import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;

    private static final long MIN = -10000L * 1000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] up = new long[N][M];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (i == N - 1 && j == 0) {
                    up[i][j] = board[i][j];
                    continue;
                }

                long left = MIN;
                long bottom = MIN;

                if (isInRange(i, j - 1)) left = up[i][j - 1];
                if (isInRange(i + 1, j)) bottom = up[i + 1][j];

                up[i][j] = Math.max(left + board[i][j], bottom + board[i][j]);
            }
        }

        long[][] down = new long[N][M];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (i == N - 1 && j == M - 1) {
                    down[i][j] = board[i][j];
                    continue;
                }
                long right = MIN;
                long bottom = MIN;

                if (isInRange(i, j + 1)) right = down[i][j + 1];
                if (isInRange(i + 1, j)) bottom = down[i + 1][j];

                down[i][j] = Math.max(right + board[i][j], bottom + board[i][j]);
            }
        }

        long answer = MIN;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, up[i][j] + down[i][j]);
            }
        }

        System.out.println(answer);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
