/*
    Boj 1932. 정수 삼각형
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1932_정수삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] triangle = new int[n][n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i+1; j++){
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i+1; j++){
                if(1 <= j && j < i){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
                }else if(j == 0){
                    dp[i][j] = dp[i-1][0] + triangle[i][j];
                }else if(j == i){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
            }
        }

        int rtn = Integer.MIN_VALUE;
        for(int cur : dp[n-1]){
            rtn = Math.max(rtn, cur);
        }

        System.out.println(rtn);
    }
}
