import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[][] board;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        int answer = bfs();

        System.out.println(answer + 1);
    }

    private static int bfs() {
        int[][][] visited = new int[N][M][K + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]][cur[2]] != 0) continue;
            visited[cur[0]][cur[1]][cur[2]] = cur[3];

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!isInRange(nx, ny)) continue;
                if (board[nx][ny] == 1 && cur[2] == K) continue;

                if (board[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny, cur[2], cur[3] + 1});
                } else if (board[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1, cur[3] + 1});
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (visited[N - 1][M - 1][i] != 0) {
                min = Math.min(min, visited[N - 1][M - 1][i]);
            }
        }

        return min == Integer.MAX_VALUE ? -2 : min;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}