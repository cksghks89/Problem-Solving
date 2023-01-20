/*
    Boj 1005. ACM Craft
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1005_ACMCraft {
    static int N, K;
    static ArrayList<Integer>[] graph;
    static int[] weight;
    static int maxTime;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            weight = new int[N + 1];
            dp = new int[N + 1];
            Arrays.fill(dp, -1);

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                graph[Y].add(X);
            }

            int W = Integer.parseInt(br.readLine());
            sb.append(dfs(W)).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int dfs(int idx) {
        if (graph[idx].size() == 0) {
            return weight[idx];
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        for (int i = 0; i < graph[idx].size(); i++) {
            int next = graph[idx].get(i);

            dp[idx] = Math.max(dp[idx], dfs(next) + weight[idx]);
        }
        return dp[idx];
    }
}
