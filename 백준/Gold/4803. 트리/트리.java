import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<Integer>[] graph;
    private static int n, m;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            graph = new List[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                if (bfs(i)) cnt += 1;
            }

            sb.append("Case ").append(idx++).append(": ");

            if (cnt == 0) {
                sb.append("No trees.\n");
            } else if (cnt == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(cnt).append(" trees.\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean bfs(int id) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{id, -1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);
                if (visited[next] && next != cur[1]) {
                    return false;
                }
                if (!visited[next]) queue.offer(new int[]{next, cur[0]});
            }
        }
        return true;
    }
}
