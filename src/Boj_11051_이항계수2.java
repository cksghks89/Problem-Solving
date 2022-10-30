/*
    Boj 11051. 이항계수 2
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11051_이항계수2 {
    static int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(combination(N, K));
    }

    static int combination(int n, int k){
        if(n == k || k == 0){
            dp[n][k] = 1;
            return 1;
        }

        if(dp[n][k] != 0){
            return dp[n][k];
        }

        return dp[n][k] = (combination(n-1, k) + combination(n-1, k-1)) % 10007;
    }
}
