import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static int N, M;
    private static int[][] map;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[501][501];

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = Math.min(input[0], input[2]);
            int y1 = Math.min(input[1], input[3]);
            int x2 = Math.max(input[0], input[2]);
            int y2 = Math.max(input[1], input[3]);

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    map[j][k] = 1;
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = Math.min(input[0], input[2]);
            int y1 = Math.min(input[1], input[3]);
            int x2 = Math.max(input[0], input[2]);
            int y2 = Math.max(input[1], input[3]);

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    map[j][k] = 2;
                }
            }
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[]{0, 0, 0});

        boolean[][] visited = new boolean[501][501];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == 500 && cur[1] == 500) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] != 2) {
                    if (map[nx][ny] == 1) pq.offer(new int[]{nx, ny, cur[2] + 1});
                    else pq.offer(new int[]{nx, ny, cur[2]});
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x <= 500 && 0 <= y && y <= 500;
    }
}
