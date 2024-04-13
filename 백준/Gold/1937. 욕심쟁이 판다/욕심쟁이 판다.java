import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] map;
    private static int[][] memo;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, getDepth(i, j));
            }
        }

        System.out.println(answer);
    }

    private static int getDepth(int x, int y) {
        if (memo[x][y] != 0) return memo[x][y];

        int cost = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isInRange(nx, ny)) continue;

            if (map[x][y] < map[nx][ny]) {
                cost = Math.max(cost, getDepth(nx, ny));
            }
        }

        return memo[x][y] = cost + 1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
