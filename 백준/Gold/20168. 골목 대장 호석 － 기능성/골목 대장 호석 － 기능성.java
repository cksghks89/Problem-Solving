import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, A, B, C;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }


        for (int i = 1; i <= 1000; i++) {
            boolean result = dijkstra(i);

            if (result) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    private static boolean dijkstra(int minCost) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{A, 0});

        int[] dist = new int[N + 1];
        Arrays.fill(dist, 11_000_000);
        dist[A] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == B) {
                return cur[1] <= C;
            }

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);

                if (next[1] <= minCost && cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new int[]{next[0], cur[1] + next[1]});
                }
            }
        }

        return false;
    }
}
