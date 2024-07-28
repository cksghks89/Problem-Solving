import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int INF = 110_000_000;
    private static int V, E, P;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        graph = new List[V + 1];
        for (int i = 0; i <= V; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        int[] path = dijkstra(1);
        int[] minjun = dijkstra(P);

        if (path[V] == path[P] + minjun[V]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{start, 0});

        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);

                if (cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist;
    }
}
