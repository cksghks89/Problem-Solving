import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int L, R, C;
    private static char[][][] map;

    private static int[] dx = {0, 0, 0, 1, 0, -1};
    private static int[] dy = {0, 0, 1, 0, -1, 0};
    private static int[] dz = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[L][R][C];
            int[] start = new int[3];
            if (L == 0 && R == 0 && C == 0) break;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = line.charAt(k);
                        if (map[i][j][k] == 'S') {
                            map[i][j][k] = '.';
                            start = new int[]{i, j, k};
                        }
                    }
                }
                br.readLine();
            }

            int cost = bfs(start);
            if (cost == -1) {
                sb.append("Trapped!").append('\n');
            } else {
                sb.append(String.format("Escaped in %d minute(s).", cost)).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int bfs(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start[0], start[1], start[2], 0});

        boolean[][][] visited = new boolean[L][R][C];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]][cur[2]]) continue;
            visited[cur[0]][cur[1]][cur[2]] = true;

            if (map[cur[0]][cur[1]][cur[2]] == 'E') return cur[3];

            for (int i = 0; i < 6; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];

                if (isInRange(nx, ny, nz) && map[nx][ny][nz] != '#' && !visited[nx][ny][nz]) {
                    queue.offer(new int[]{nx, ny, nz, cur[3] + 1});
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y, int z) {
        return 0 <= x && x < L && 0 <= y && y < R && 0 <= z && z < C;
    }
}
