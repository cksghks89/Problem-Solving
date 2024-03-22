import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static char[][] map;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static Queue<int[]> fire;
    private static boolean[][] fireVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fire = new ArrayDeque<>();
        fireVisited = new boolean[R][C];
        int sx = 0;
        int sy = 0;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'J') {
                    sx = i;
                    sy = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'F') {
                    fire.offer(new int[]{i, j, 0});
                }
            }
        }

        fireSpread();
        int answer = bfs(sx, sy);

        if (answer == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(answer);
    }

    private static int bfs(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});

        boolean[][] visited = new boolean[R][C];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (isBound(cur[0], cur[1])) return cur[2] + 1;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if ((isInRange(nx, ny) && map[nx][ny] != '#' && (char) ('0' + cur[2] + 1) < map[nx][ny]) || map[nx][ny] == '.') {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        return -1;
    }

    private static void fireSpread() {
        while (!fire.isEmpty()) {
            int[] cur = fire.poll();

            if (fireVisited[cur[0]][cur[1]]) continue;
            fireVisited[cur[0]][cur[1]] = true;

            map[cur[0]][cur[1]] = (char)('0' + cur[2]);

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == '.' && !fireVisited[nx][ny]) {
                    fire.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
    }

    private static boolean isBound(int x, int y) {
        return x == 0 || x == R - 1 || y == 0 || y == C - 1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
