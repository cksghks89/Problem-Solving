/*
    Boj 2293. 동전 1
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2293_동전1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for(int i = 0; i < n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];
        dp[0] = 1;

        for(int i = 0; i < n; i++){
            for(int j = 1; j <= k; j++){
                if(coins[i] <= j){
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }

        System.out.println(dp[k]);
    }
}
