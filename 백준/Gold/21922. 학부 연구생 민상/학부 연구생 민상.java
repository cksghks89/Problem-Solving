import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;

    // 상, 우, 하, 좌
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static boolean[][] answer;
    private static List<int[]> airConditioner;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        answer = new boolean[N][M];
        airConditioner = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    airConditioner.add(new int[]{i, j});
                    board[i][j] = 0;
                }
            }
        }


        for (int i = 0; i < airConditioner.size(); i++) {
            int[] cur = airConditioner.get(i);
            doAirConditioner(cur[0], cur[1]);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (answer[i][j]) count += 1;
            }
        }

        System.out.println(count);
    }

    private static void doAirConditioner(int x, int y) {
        int[][] change = {
                {},
                {0, 2, 0, 2},
                {2, 0, 2, 0},
                {1, 3, 1, 3},
                {3, 1, 3, 1}
        };

        boolean[][][] visited = new boolean[4][N][M];
        for (int i = 0; i < 4; i++) {
            int d = i;
            int nx = x;
            int ny = y;

            while (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (visited[d][nx][ny]) break;

                visited[d][nx][ny] = true;
                answer[nx][ny] = true;

                if (board[nx][ny] != 0) {
                    d += change[board[nx][ny]][d];
                    d %= 4;
                }

                nx += dx[d];
                ny += dy[d];
            }
        }
    }
}