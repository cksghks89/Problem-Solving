import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || board[i][j] == 1) continue;
                count += 1;
                bfs(i, j);
            }
        }

        System.out.println(count);
    }

    private static void bfs(int x, int y) {
        queue.clear();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = (cur[0] + dx[i] + N) % N;
                int ny = (cur[1] + dy[i] + M) % M;

                if (board[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
