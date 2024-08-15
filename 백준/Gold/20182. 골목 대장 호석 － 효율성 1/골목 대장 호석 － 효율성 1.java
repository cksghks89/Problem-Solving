import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int INF = 110_000_000;
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
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s].add(new int[]{e, w});
            graph[s].add(new int[]{s, w});
        }

        int answer = -1;
        for (int i = 1; i <= 20; i++) {
            boolean result = dijkstra(i);
            if (result) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean dijkstra(int max) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{A, 0});
        dist[A] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);

                if (next[1] <= max && cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist[B] <= C;
    }
}
