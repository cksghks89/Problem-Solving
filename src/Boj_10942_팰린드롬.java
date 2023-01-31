/*
    Boj 10942. 팰린드롬?
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_10942_팰린드롬 {
    static int N;
    static int[] nums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], 2);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(isPalindrome(S, E)).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int isPalindrome(int x, int y) {
        if (x == y || x > y) {
            dp[x][y] = 1;
            return 1;
        }

        if (nums[x] != nums[y]) {
            dp[x][y] = 0;
            return 0;
        }

        if (dp[x + 1][y - 1] != 2) {
            dp[x][y] = dp[x + 1][y - 1];
        } else if (dp[x + 1][y - 1] == 2) {
            dp[x][y] = isPalindrome(x + 1, y - 1);
        }

        return dp[x][y];
    }
}
