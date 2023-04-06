import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] out;
    static boolean[][] visited;
    static List<int[]> twoList;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int allCnt;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        out = new int[M][];
        twoList = new ArrayList<>();
        result = Integer.MAX_VALUE;

        int wallCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) twoList.add(new int[]{i, j});
                if (map[i][j] == 1) wallCnt++;
            }
        }
        allCnt = N * N - wallCnt;

        comb(0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void comb(int cnt, int start) {
        if (cnt == M) {
            result = Math.min(result, bfs());
            return;
        }

        for (int i = start; i < twoList.size(); i++) {
            out[cnt] = twoList.get(i);
            comb(cnt + 1, i + 1);
        }
    }

    static int bfs() {
        visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            queue.offer(new int[]{out[i][0], out[i][1], 0});
        }

        int max = 0;
        int visitCnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            visitCnt++;

            max = Math.max(max, cur[2]);

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        if (visitCnt == allCnt) {
            return max;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
