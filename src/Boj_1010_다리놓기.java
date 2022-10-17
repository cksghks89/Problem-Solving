/*
    Boj 1010. 다리 놓기
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1010_다리놓기 {
    static int[][] dp = new int[31][31];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int test = 0; test < T; test++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(dfs(N, M));
        }
    }

    static int dfs(int n, int m){
        if(n == m || n == 0){
            dp[n][m] = 1;
            return 1;
        }

        if(dp[n][m] != 0){
            return dp[n][m];
        }

        int rtn = 0;
        for(int i = 0; i < m-n+1; i++){
            rtn += dfs(n-1, m-i-1);
        }
        dp[n][m] = rtn;

        return dp[n][m];
    }
}
