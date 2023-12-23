import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, M, K;
    private static int[] candy;
    private static List<int[]> group;
    private static List<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        group = new ArrayList<>();
        graph = new List[N + 1];
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            group.add(bfs(i));
        }

        int[][] dp = new int[group.size()][K];
        for (int i = group.get(0)[0]; i < K; i++) {
            dp[0][i] = group.get(0)[1];
        }

        for (int i = 1; i < group.size(); i++) {
            for (int j = 1; j < K; j++) {
                if (j - group.get(i)[0] >= 0) {
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - group.get(i)[0]] + group.get(i)[1]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[group.size() - 1][K - 1]);
    }

    private static int[] bfs(int idx) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(idx);

        int candyCnt = 0;
        int childCnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;
            candyCnt += candy[cur];
            childCnt += 1;

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);

                if (!visited[next]) {
                    queue.offer(next);
                }
            }
        }
        return new int[]{childCnt, candyCnt};
    }
}
