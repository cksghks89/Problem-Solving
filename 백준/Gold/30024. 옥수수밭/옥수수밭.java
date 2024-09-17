import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;

    private static PriorityQueue<int[]> pq;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        pq = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    pq.offer(new int[]{i, j, board[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            if (pq.isEmpty()) continue;

            int[] cur = pq.poll();
            sb.append(cur[0] + 1).append(' ').append(cur[1] + 1).append('\n');

            for (int j = 0; j < 4; j++) {
                int nx = cur[0] + dx[j];
                int ny = cur[1] + dy[j];

                if (isInRange(nx, ny) && !visited[nx][ny]) {
                    pq.offer(new int[]{nx, ny, board[nx][ny]});
                    visited[nx][ny] = true;
                }
            }
        }

        System.out.println(sb);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
