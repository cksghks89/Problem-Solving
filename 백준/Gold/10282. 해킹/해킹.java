import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<int[]>[] graph;
    private static int n, d, c;
    private static final int INF = 11_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph = new List[n + 1];
            for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new int[]{a, s});
            }

            int[] dist = dijkstra(c);

            int max = 0;
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] == INF) continue;
                max = Math.max(max, dist[i]);
                cnt += 1;
            }

            sb.append(cnt).append(' ').append(max).append('\n');
        }
        System.out.println(sb);
    }

    private static int[] dijkstra(int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{x, 0});

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[x] = 0;

        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);

                if (dist[next[0]] > cur[1] + next[1]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist;
    }
}
