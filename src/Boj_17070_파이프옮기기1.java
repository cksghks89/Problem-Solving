/*
    Boj 17070. 파이프 옮기기 1
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17070_파이프옮기기1 {
    static int N;
    static int[][] map;
    static int cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);

        System.out.println(cost);
    }

    static void dfs(int x, int y, int status) {
        if (x == N - 1 && y == N - 1) {
            cost += 1;
            return;
        }

        if (status == 0 || status == 2) {
            if (isInRange(x, y + 1) && map[x][y + 1] == 0) {
                dfs(x, y + 1, 0);
            }
        }

        if (status == 1 || status == 2) {
            if (isInRange(x + 1, y) && map[x + 1][y] == 0) {
                dfs(x + 1, y, 1);
            }
        }

        if (isInRange(x + 1, y + 1) && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, 2);
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}