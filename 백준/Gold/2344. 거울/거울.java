import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] box;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        box = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 1;
        for (int i = 1; i <= N; i++) {
            box[i][0] = idx++;
        }
        for (int i = 1; i <= M; i++) {
            box[N + 1][i] = idx++;
        }
        for (int i = N; i >= 1; i--) {
            box[i][M + 1] = idx++;
        }
        for (int i = M; i >= 1; i--) {
            box[0][i] = idx++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int cur = bfs(i, 0, 0);
            sb.append(cur).append(' ');
        }

        for (int i = 1; i <= M; i++) {
            int cur = bfs(N + 1, i, 3);
            sb.append(cur).append(' ');
        }

        for (int i = N; i >= 1; i--) {
            int cur = bfs(i, M + 1, 2);
            sb.append(cur).append(' ');
        }

        for (int i = M; i >= 1; i--) {
            int cur = bfs(0, i, 1);
            sb.append(cur).append(' ');
        }

        System.out.println(sb);
    }

    private static int bfs(int x, int y, int d) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, d});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int nx = cur[0] + dx[cur[2]];
            int ny = cur[1] + dy[cur[2]];

            if (!isInRange(nx, ny)) {
                return box[nx][ny];
            }

            if (box[nx][ny] == 1) {
                // 0 -> 3, 1 -> 2, 2 -> 1, 3 -> 0
                if (cur[2] == 0) {
                    queue.offer(new int[]{nx, ny, 3});
                } else if (cur[2] == 3) {
                    queue.offer(new int[]{nx, ny, 0});
                } else if (cur[2] == 1) {
                    queue.offer(new int[]{nx, ny, 2});
                } else {
                    queue.offer(new int[]{nx, ny, 1});
                }
            } else {
                queue.offer(new int[]{nx, ny, cur[2]});
            }
        }

        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= M;
    }
}
