import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, R, Q;
    private static List<Integer>[] graph;
    private static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        memo = new int[N + 1];

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(R, new boolean[N + 1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(memo[u]).append('\n');
        }

        System.out.println(sb);
    }

    private static int dfs(int id, boolean[] visited) {
        int count = 1;
        visited[id] = true;
        for (int i = 0; i < graph[id].size(); i++) {
            int child = graph[id].get(i);
            if (visited[child]) continue;
            count += dfs(child, visited);
        }

        return memo[id] = count;
    }
}
