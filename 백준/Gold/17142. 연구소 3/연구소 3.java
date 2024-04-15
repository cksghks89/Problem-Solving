import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static List<int[]> virus;

    private static int answer = Integer.MAX_VALUE;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        comb(0, 0, new int[M]);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void comb(int start, int cnt, int[] out) {
        if (cnt == M) {
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < out.length; i++) {
                int[] cur = virus.get(out[i]);
                queue.offer(new int[]{cur[0], cur[1], 0});
            }

            answer = Math.min(answer, bfs(queue));
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            out[cnt] = i;
            comb(i + 1, cnt + 1, out);
        }
    }

    private static int bfs(Queue<int[]> queue) {
        boolean[][] visited = new boolean[N][N];

        int time = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (map[cur[0]][cur[1]] == 0) time = Math.max(time, cur[2]);

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] != 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        if (isAllSpread(visited)) return time;
        else return Integer.MAX_VALUE;
    }

    private static boolean isAllSpread(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
