import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int w, h;
    private static int[][] map;

    private static final int R = -2;
    private static final int H = -3;
    private static final int E = -4;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        int sx = -1, sy = -1;

        for (int i = 0; i < h; i++) {
            String input = br.readLine();
            for (int j = 0; j < w; j++) {
                char cur = input.charAt(j);
                if (cur == 'E') {
                    map[i][j] = E;
                } else if (cur == 'R') {
                    map[i][j] = R;
                } else if (cur == 'H') {
                    map[i][j] = H;
                } else if (cur == 'T') {
                    map[i][j] = 0;
                    sx = i;
                    sy = j;
                } else {
                    map[i][j] = cur - '0';
                }
            }
        }

        int answer = dijkstra(sx, sy);

        System.out.println(answer);
    }

    private static int dijkstra(int x, int y) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[]{x, y, 0});

        boolean[][] visited = new boolean[h][w];
        // 1. 현재 위치에서 4방향 탐색 -> 가능한 부분만 미끄럼 시간과 함께 pq에 넣기
        // 2. 출구에 도달했다면 종료
        // 3. 진행 과정은 방문체크를 통해 동일 지역에 도달하는 것을 방지

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (map[cur[0]][cur[1]] == E) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int[] dest = getDest(cur[0], cur[1], i);
                if (dest == null) continue;
                pq.offer(new int[]{dest[0], dest[1], cur[2] + dest[2]});
            }
        }
        return -1;
    }

    private static int[] getDest(int x, int y, int d) {
        int nx = x;
        int ny = y;
        int time = 0;
        while (true) {
            nx += dx[d];
            ny += dy[d];

            if (!isInRange(nx, ny) || map[nx][ny] == H) break;

            if (map[nx][ny] == E) {
                return new int[]{nx, ny, time};
            }
            if (map[nx][ny] == R) {
                return new int[]{nx - dx[d], ny - dy[d], time};
            }
            time += map[nx][ny];
        }

        return null;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
}
