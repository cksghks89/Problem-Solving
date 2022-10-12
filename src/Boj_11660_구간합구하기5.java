/*
    Boj 11660. 구간 합 구하기 5
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11660_구간합구하기5 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int[][] pos = new int[M][4];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                pos[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = map[i-1][j-1] + dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1];
            }
        }

        for(int i = 0; i < M; i++){
            int x1 = pos[i][0] - 1;
            int y1 = pos[i][1] - 1;
            int x2 = pos[i][2];
            int y2 = pos[i][3];

            System.out.println(
                    dp[x2][y2] - dp[x2][y1] - dp[x1][y2] + dp[x1][y1]
            );
        }
    }
}
