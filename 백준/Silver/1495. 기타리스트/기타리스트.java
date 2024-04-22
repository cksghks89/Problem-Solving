import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        boolean[][] dp = new boolean[N + 1][M + 1];
        
        dp[0][S] = true;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            for(int j = 0; j <= M; j++) {
                if (j - cur >= 0 && dp[i -1][j - cur]) {
                    dp[i][j] = true;
                }
                if (j + cur <= M && dp[i - 1][j + cur]) {
                    dp[i][j] = true;
                }
            }
        }
        
        for (int i = M; i >= 0; i--) {
            if (dp[N][i]) {
                System.out.println(i);
                return;
            }
        }
        
        System.out.println(-1);
    }
}