/*
    Boj 1890. 점프
    level. silver 1
    solved by 송찬환
 */
package boj_dp_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1890_점프 {
    static int[][] map;
    static long[][] dp;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    break;
                }
                possiblePath(i, j);
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }

    static void possiblePath(int x, int y) {
        int n = map[x][y];

        if (isInRange(x + n, y)) {
            dp[x + n][y] += dp[x][y];
        }
        if (isInRange(x, y + n)) {
            dp[x][y + n] += dp[x][y];
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
