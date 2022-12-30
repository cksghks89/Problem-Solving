/*
    Boj 2098. 외판원 순회
    level. gold 1
    solved by 송찬환 with https://maivve.tistory.com/306
 */
package boj_dp_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2098_외판원순회 {
    static int N;
    static int[][] graph;
    static int[][] dp;

    static final int INF = 11_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new int[N][(1 << N) - 1];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tsp(0, 1));
        System.out.println();
    }

    static int tsp(int src, int mask) {
        if (mask == (1 << N) - 1) {
            if(graph[src][0] == 0){
                return INF;
            }
            return graph[src][0];
        }

        if (dp[src][mask] != 0) {
            return dp[src][mask];
        }
        dp[src][mask] = INF;

        for (int i = 0; i < N; i++) {
            if ((mask & 1 << i) == 0 && graph[src][i] != 0) {
                dp[src][mask] = Math.min(dp[src][mask], tsp(i, mask | (1 << i)) + graph[src][i]);
            }
        }

        return dp[src][mask];
    }
}
