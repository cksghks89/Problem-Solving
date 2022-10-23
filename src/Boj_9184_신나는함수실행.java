/*
    Boj 9184. 신나는 함수 실행
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_9184_신나는함수실행 {
    static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1){
                break;
            }

            int rtn = w(a, b, c);
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ")
                    .append(rtn).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            if(dp[0][0][0] == 0){
                dp[0][0][0] = 1;
            }
            return dp[0][0][0];
        }
        if (a > 20 || b > 20 || c > 20) {
            if(dp[20][20][20] == 0){
                dp[20][20][20] = w(20, 20, 20);
            }
            return dp[20][20][20];
        }

        if(dp[a][b][c] != 0){
            return dp[a][b][c];
        }

        if (a < b && b < c) {
            dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
            return dp[a][b][c];
        }

        dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c)
                + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        return dp[a][b][c];
    }
}
