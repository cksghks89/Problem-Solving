import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    private static int N;
    private static int[][] map;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static boolean[][] visited;

    private static Queue<int[]> checkQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        checkQueue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int id = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    initBfs(i, j, id++);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        while (!checkQueue.isEmpty()) {
            visited = new boolean[N][N];
            int[] cur = checkQueue.poll();

            int curDist = getDistanceBfs(cur[0], cur[1], map[cur[0]][cur[1]]);
            answer = Math.min(answer, curDist);
        }

        System.out.println(answer);
    }

    private static int getDistanceBfs(int x, int y, int id) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] != id) {
                return cur[2] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != id) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static void initBfs(int x, int y, int id) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            map[cur[0]][cur[1]] = id;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == 0) {
                    checkQueue.offer(new int[]{cur[0], cur[1]});
                }

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
