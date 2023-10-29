import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] map;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;


            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ")
                    .append(num++)
                    .append(": ")
                    .append(dijkstra())
                    .append('\n');
        }

        System.out.println(sb);
    }

    private static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        pq.offer(new int[]{0, 0, map[0][0]});

        boolean[][] visited = new boolean[N][N];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == N - 1 && cur[1] == N - 1) {
                return cur[2];
            }
            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny]) {
                    pq.offer(new int[]{nx, ny, cur[2] + map[nx][ny]});
                }
            }
        }
        return 0;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
