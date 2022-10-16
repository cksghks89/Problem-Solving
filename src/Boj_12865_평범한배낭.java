/*
    Boj 12865. 평번한 배낭
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_12865_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] items = new int [N][2];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K + 1];
        for(int i = 0; i < N; i++){
            for(int j = 1; j <= K; j++){
                if(items[i][0] > j){
                    dp[i+1][j] = dp[i][j];
                }else{
                    dp[i+1][j] = Math.max(dp[i][j], dp[i][j - items[i][0]] + items[i][1]);
                }
            }
        }

        int rtn = Integer.MIN_VALUE;
        for(int cur : dp[N]){
            rtn = Math.max(rtn, cur);
        }
        System.out.println(rtn);
    }
}
