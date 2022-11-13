/*
    Boj 2294. 동전 2
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2294_동전2 {
    static int n;
    static int k;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(br.readLine());
            coins[i] = cur;
        }
        Arrays.fill(dp, -1);
        dp[0] = 0;
        Arrays.sort(coins);

        System.out.println(getMinCoins());
    }

    static int getMinCoins() {
        for (int i = 1; i < k + 1; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < coins.length; j++) {
                if (!isInRange(i - coins[j])) {
                    break;
                }
                if(dp[i-coins[j]] == -1){
                    continue;
                }
                min = Math.min(min, dp[i - coins[j]] + 1);
            }
            if(min == Integer.MAX_VALUE){
                dp[i] = -1;
            }else{
                dp[i] = min;
            }
        }

        return dp[k];
    }

    static boolean isInRange(int x) {
        return 0 <= x && x <= k;
    }
}
