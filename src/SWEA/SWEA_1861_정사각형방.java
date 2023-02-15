package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방 {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N][N];
            map = new int[N][N];

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    pq.offer(new int[]{i, j, map[i][j]});
                }
            }

            int roomNumber = 0;
            int maxCnt = Integer.MIN_VALUE;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (!visited[cur[0]][cur[1]]) {
                    int cnt = bfs(cur[0], cur[1]);
                    if (maxCnt < cnt) {
                        maxCnt = cnt;
                        roomNumber = cur[2];
                    }
                }
            }
            sb.append(String.format("#%d %d %d\n", tc, roomNumber, maxCnt));
        }
        System.out.print(sb.toString());
    }

    static int bfs(int x, int y) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == map[cur[0]][cur[1]] + 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return cnt;
    }

    private static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
