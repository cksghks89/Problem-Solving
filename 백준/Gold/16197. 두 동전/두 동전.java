import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static char[][] map;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int[] coin = new int[4];
        int coinIdx = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'o') {
                    coin[coinIdx++] = i;
                    coin[coinIdx++] = j;
                }
            }
        }

        int answer = bfs(coin[0], coin[1], coin[2], coin[3]);
        System.out.println(answer);
    }

    private static int bfs(int x1, int y1, int x2, int y2) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x1, y1, x2, y2, 0});

        boolean[][][][] visited = new boolean[N][M][N][M];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]][cur[2]][cur[3]]) continue;
            visited[cur[0]][cur[1]][cur[2]][cur[3]] = true;

            if (cur[4] > 10) return -1;

            for (int i = 0; i < 4; i++) {
                int nx1 = cur[0] + dx[i];
                int ny1 = cur[1] + dy[i];
                int nx2 = cur[2] + dx[i];
                int ny2 = cur[3] + dy[i];

                if (isInRange(nx1, ny1) && map[nx1][ny1] == '#') {
                    nx1 = cur[0];
                    ny1 = cur[1];
                }
                if (isInRange(nx2, ny2) && map[nx2][ny2] == '#') {
                    nx2 = cur[2];
                    ny2 = cur[3];
                }

                if (nx1 == nx2 && ny1 == ny2) continue;

                if (isInRange(nx1, ny1) && isInRange(nx2, ny2)) {
                    queue.offer(new int[]{nx1, ny1, nx2, ny2, cur[4] + 1});
                } else if ((!isInRange(nx1, ny1) && isInRange(nx2, ny2)) || (isInRange(nx1, ny1) && !isInRange(nx2, ny2))) {
                    return cur[4] + 1 > 10 ? -1 : cur[4] + 1;
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
