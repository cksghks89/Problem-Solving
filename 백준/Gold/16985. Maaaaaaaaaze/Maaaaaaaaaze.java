import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][][] original;
    private static boolean[][][] cube;

    private static final int[] dx = {0, 0, 0, 0, 1, -1};
    private static final int[] dy = {0, 0, 1, -1, 0, 0};
    private static final int[] dz = {1, -1, 0, 0, 0, 0};

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        original = new boolean[5][5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    original[i][j][k] = "1".equals(st.nextToken());
                }
            }
        }

        subSet(0, new int[5]);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void subSet(int cnt, int[] rotate) {
        if (cnt == 5) {
            cube = new boolean[5][5][5];
            for (int i = 0; i < 5; i++) {
                boolean[][] cur = original[i];
                for (int j = 0; j < rotate[i]; j++) {
                    cur = rotate(cur);
                }
                cube[i] = cur;
            }

            perm(0, 0, new int[5]);
            return;
        }

        for (int i = 0; i < 4; i++) {
            rotate[cnt] = i;
            subSet(cnt + 1, rotate);
        }
    }

    private static boolean[][] rotate(boolean[][] board) {
        boolean[][] result = new boolean[5][5];

        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                result[j][k] = board[4 - k][j];
            }
        }

        return result;
    }

    private static void perm(int cnt, int visited, int[] out) {
        if (cnt == 5) {
            boolean[][][] newCube = new boolean[5][5][5];
            for (int i = 0; i < 5; i++) {
                newCube[i] = cube[out[i]];
            }

            answer = Math.min(answer, bfs(newCube, new int[]{0, 0, 0}, new int[]{4, 4, 4}));
            return;
        }

        for (int i = 0; i < 5; i++) {
            if ((visited & (1 << i)) > 0) continue;
            out[cnt] = i;
            perm(cnt + 1, visited | (1 << i), out);
        }
    }

    private static int bfs(boolean[][][] newCube, int[] s, int[] e) {
        if (!newCube[s[0]][s[1]][s[2]]) return Integer.MAX_VALUE;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{s[0], s[1], s[2], 0});

        boolean[][][] visited = new boolean[5][5][5];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (answer != Integer.MAX_VALUE && answer <= cur[3]) return Integer.MAX_VALUE;

            if (visited[cur[0]][cur[1]][cur[2]]) continue;
            visited[cur[0]][cur[1]][cur[2]] = true;

            if (cur[0] == e[0] && cur[1] == e[1] && cur[2] == e[2]) {
                return cur[3];
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];

                if (isInRange(nx, ny, nz) && newCube[nx][ny][nz] && !visited[nx][ny][nz]) {
                    queue.offer(new int[]{nx, ny, nz, cur[3] + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean isInRange(int x, int y, int z) {
        return 0 <= x && x < 5 && 0 <= y && y < 5 && 0 <= z && z < 5;
    }
}
