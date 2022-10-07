/*
    Boj 1149. RGB거리
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1149_RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N+1][];

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            house[i] = new int[3];
            house[i][0] = Integer.parseInt(st.nextToken());
            house[i][1] = Integer.parseInt(st.nextToken());
            house[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[3][N + 1];

        // 0 - red, 1 - green, 2 - blue
        for(int i = 1; i <= N; i++){
            if(i == 1){
                dp[0][1] = house[i][0];
                dp[1][1] = house[i][1];
                dp[2][1] = house[i][2];
                continue;
            }

            dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + house[i][0];
            dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + house[i][1];
            dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + house[i][2];
        }
        System.out.println(Math.min(dp[0][N], Math.min(dp[1][N], dp[2][N])));
    }
}
