import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int w, h;
    private static char[][] map;
    private static Queue<int[]> queue;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            queue = new ArrayDeque<>();

            int sx = -1;
            int sy = -1;

            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    if (input.charAt(j) == '@') {
                        sx = i;
                        sy = j;
                        map[i][j] = '.';
                    } else if (input.charAt(j) == '*') {
                        queue.offer(new int[]{i, j, 0});
                        map[i][j] = '*';
                    } else if (input.charAt(j) == '#') {
                        map[i][j] = '#';
                    } else {
                        map[i][j] = '.';
                    }
                }
            }

            int result = bfs(sx, sy);
            if (result == -1) sb.append("IMPOSSIBLE").append('\n');
            else sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});

        boolean[][] visited = new boolean[h][w];

        int time = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (time == cur[2]) {
                spread(time);
                time += 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == '.') {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }

                if (!isInRange(nx, ny)) {
                    return cur[2] + 1;
                }
            }
        }

        return -1;
    }

    private static void spread(int time) {
        while (!queue.isEmpty()) {
            if (queue.peek()[2] > time) break;

            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
}
