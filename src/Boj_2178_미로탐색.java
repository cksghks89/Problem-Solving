/*
    Boj 2178. 미로 탐색
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2178_미로탐색 {
    static int[][] maze;
    static int N;
    static int M;

    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};

    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        dist = new int[N][M];

        for(int i = 0; i < N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0, 0, 1);
        System.out.println(dist[N-1][M-1]);
    }

    static void dfs(int x, int y, int cnt) {
        if (x == N - 1 && y == M - 1) {
            return;
        }

        for (int k = 0; k < 4; k++) {
            if (0 <= x + moveX[k] && x + moveX[k] < N && 0 <= y + moveY[k] && y + moveY[k] < M) {
                if (dist[x+moveX[k]][y+moveY[k]] > cnt + 1 && maze[x + moveX[k]][y + moveY[k]] != 0) {
                    dist[x + moveX[k]][y + moveY[k]] = cnt+1;
                    dfs(x + moveX[k], y + moveY[k], cnt + 1);
                }
            }
        }
    }
}
