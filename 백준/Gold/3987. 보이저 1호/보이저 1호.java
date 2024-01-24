import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static char[][] map;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static boolean[][][] visited;

    private static char[] convert = {'U', 'R', 'D', 'L'};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int pr = Integer.parseInt(st.nextToken()) - 1;
        int pc = Integer.parseInt(st.nextToken()) - 1;

        // 0, 1, 2, 3 (위, 오른, 아래, 왼)
        int maxCnt = -1;
        int d = -1;
        for (int i = 0; i < 4; i++) {
            visited = new boolean[N][M][4];
            int result = dfs(pr, pc, i, 0);

            if (result == Integer.MAX_VALUE) {
                System.out.println(convert[i]);
                System.out.println("Voyager");
                return;
            }

            if (maxCnt < result) {
                maxCnt = result;
                d = i;
            }
        }

        System.out.println(convert[d]);
        System.out.println(maxCnt);
    }

    private static int dfs(int r, int c, int d, int cnt) {
        if (!isInRange(r, c)) {
            return cnt;
        }
        if (map[r][c] == 'C') {
            return cnt;
        }
        if (visited[r][c][d]) {
            return Integer.MAX_VALUE;
        }
        visited[r][c][d] = true;

        int nr = r;
        int nc = c;

        if (map[r][c] == '/') {
            // 0 -> 1, 1 -> 0, 2 -> 3, 3 -> 2
            d = d == 0 || d == 2 ? d + 1 : d - 1;
            nr += dx[d];
            nc += dy[d];
        } else if (map[r][c] == '\\') {
            // 0 -> 3, 3 -> 0, 1 -> 2, 2 -> 1
            if (d == 0) d = 3;
            else if (d == 3) d = 0;
            else if (d == 1) d = 2;
            else if (d == 2) d = 1;

            nr += dx[d];
            nc += dy[d];
        } else if (map[r][c] == '.') {
            nr += dx[d];
            nc += dy[d];
        }

        return dfs(nr, nc, d, cnt + 1);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
