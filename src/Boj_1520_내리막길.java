/*
    Boj 1520. 내리막길
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1520_내리막길 {
    static int N;
    static int M;

    static int[][] map;
    static int[][] dp;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        boolean isChange = true;
        while(isChange){
            isChange = false;

            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(i == 0 && j == 0){
                        continue;
                    }
                    int sum = sumAround(i, j);
                    if(dp[i][j] != sum){
                        isChange = true;
                        dp[i][j] = sum;
                    }
                }
            }
        }

        System.out.println(dp[N-1][M-1]);
    }

    static int sumAround(int x, int y){
        int sum = 0;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isInRange(nx, ny) && map[x][y] < map[nx][ny]){
                sum += dp[nx][ny];
            }
        }
        return sum;
    }

    static boolean isInRange(int x, int y){
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}
