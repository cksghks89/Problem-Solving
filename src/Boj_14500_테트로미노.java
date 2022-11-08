/*
    Boj 14500. 테트로미노
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14500_테트로미노 {
    static int[][] map;
    static boolean[][] visited;

    static int result = Integer.MIN_VALUE;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                dfs(i, j, 1, 0);
                calcNoDfsBlock(i, j);
            }
        }

        System.out.println(result);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            result = Math.max(result, sum + map[x][y]);
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isInRange(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[x][y]);
                visited[nx][ny] = false;
            }
        }
        visited[x][y] = false;
    }

    static void calcNoDfsBlock(int x, int y){
        int max = Integer.MIN_VALUE;

        // 1
        int[] dx = {0, 0, 0, -1};
        int[] dy = {0, 1, 2, 1};
        max = Math.max(max, calcBlock(x, y, dx, dy));

        // 2
        dx = new int[]{0, 0, 0, 1};
        dy = new int[]{0, 1, 2, 1};
        max = Math.max(max, calcBlock(x, y, dx, dy));

        // 3
        dx = new int[]{0, 1, 2, 1};
        dy = new int[]{0, 0, 0, -1};
        max = Math.max(max, calcBlock(x, y, dx, dy));

        // 4
        dx = new int[]{0, 1, 2, 1};
        dy = new int[]{0, 0, 0, 1};
        max = Math.max(max, calcBlock(x, y, dx, dy));

        result = Math.max(result, max);
    }

    static int calcBlock(int x, int y, int[] dx, int[] dy){
        int sum = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isInRange(nx, ny)){
                sum += map[nx][ny];
            }else{
                return Integer.MIN_VALUE;
            }
        }
        return sum;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}
