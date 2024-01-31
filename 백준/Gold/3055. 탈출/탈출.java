import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static char[][] map;
    private static int[][] waterMap;
    private static int[] end;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        waterMap = new int[R][C];
        int[] start = new int[2];
        end = new int[2];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                waterMap[i][j] = Integer.MAX_VALUE;
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'D') {
                    end[0] = i;
                    end[1] = j;
                } else if (map[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                    map[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') waterBfs(i, j);
            }
        }

        int result = bfs(start[0], start[1]);

        if (result == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});

        boolean[][] visited = new boolean[R][C];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == end[0] && cur[1] == end[1]) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == 'D') && waterMap[nx][ny] > cur[2] + 1) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return -1;
    }

    private static void waterBfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});

        boolean[][] visited = new boolean[R][C];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            waterMap[cur[0]][cur[1]] = cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == '.' && !visited[nx][ny] && (waterMap[nx][ny] == 0 || waterMap[nx][ny] > cur[2] + 1)) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
