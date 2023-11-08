import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                result += 1;
                bfs(i);
            }
        }

        System.out.println(result);
    }

    private static void bfs(int idx) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(idx);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if (!visited[next]) {
                    queue.offer(next);
                }
            }
        }
    }
}
