import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] map;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static boolean[][] visited;
    private static int[][] blockCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        blockCnt = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - 'A';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    boolean cycle = dfs(i, j, 1, map[i][j]);
                    if (cycle) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }

        System.out.println("No");
    }

    private static boolean dfs(int x, int y, int cnt, int block) {
        visited[x][y] = true;
        blockCnt[x][y] = cnt;

        boolean result = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isInRange(nx, ny) && map[nx][ny] == block && visited[nx][ny] && cnt - blockCnt[nx][ny] > 1) {
                return true;
            }

            if (isInRange(nx, ny) && map[nx][ny] == block && !visited[nx][ny]) {
                if (dfs(nx, ny, cnt + 1, block)) {
                    result = true;
                }
            }
        }
        return result;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
